package com.bjhy.news.rpc.api.netty.proxy;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.bjhy.news.common.exception.NewsRpcException;
import com.bjhy.news.common.notify.AbstractNotifyListener;
import com.bjhy.news.common.notify.NotifyListener;
import com.bjhy.news.common.util.NewsConstants;
import com.bjhy.news.common.util.NewsRpcUtil;
import com.bjhy.news.rpc.api.netty.codec.RpcDecoder;
import com.bjhy.news.rpc.api.netty.codec.RpcEncoder;
import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.domain.RpcResponse;

import cn.wulin.brace.utils.LoggerUtils;
import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * RPC 客户端（用于发送 RPC 请求）
 * @author wulin
 */
public class NettyRpcClient extends AbstractNotifyListener{
	
    private static final long LOCK_TIMEOUT_MILLIS = 3000;
    
    /**
     * 连接超时时间
     */
    private static final long CONNECT_TIMEOUT = 30000;
    
    private final Lock lockChannelTables = new ReentrantLock();
    
    private final ConcurrentMap<String /* addr */, ChannelWrapper> channelTables = new ConcurrentHashMap<String, ChannelWrapper>();
    
    private Bootstrap bootstrap = new Bootstrap();
    
    private NettyRpcClientHandler clientHandler= new NettyRpcClientHandler();
    
    public Channel getChannel(RpcRequest request) {
    	try {
			Channel channel = getAndCreateChannel(request.getHost()+":"+request.getPort());
			if(channel == null) {
				throw new IllegalAccessError("连接主机被拒绝! 主机: "+request.getHost()+":"+request.getPort());
			}
			return channel;
		} catch (Exception e) {
			LoggerUtils.error(e.getMessage());
			return null;
		}
    }
    
    public NettyRpcClientHandler getClientHandler() {
		return clientHandler;
	}

	/**
     * 创建NettyClient
     */
	public void createNettyClient() {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup(4);
		bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>(){
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						ChannelPipeline cp = socketChannel.pipeline();
				        cp.addLast(new RpcEncoder(RpcRequest.class));
				        cp.addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 0));
				        cp.addLast(new RpcDecoder(RpcResponse.class));
				        cp.addLast(clientHandler);
					}
                });
        
	}
	
	/**
	 * 创建并得到channel
	 * @param addr
	 * @return
	 * @throws InterruptedException
	 */
	private Channel getAndCreateChannel(final String addr) throws InterruptedException {

        ChannelWrapper cw = this.channelTables.get(addr);
        if (cw != null && cw.isOK()) {
            return cw.getChannel();
        }

        return this.createChannel(addr);
    }
	
	/**
	 * 创建channel
	 * @param addr
	 * @return
	 * @throws InterruptedException
	 */
	 private Channel createChannel(final String addr) throws InterruptedException {
	        ChannelWrapper cw = this.channelTables.get(addr);
	        if (cw != null && cw.isOK()) {
	            cw.getChannel().close();
	            channelTables.remove(addr);
	        }

	        if (this.lockChannelTables.tryLock(LOCK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)) {
	            try {
	                boolean createNewConnection;
	                cw = this.channelTables.get(addr);
	                if (cw != null) {

	                    if (cw.isOK()) {
	                        cw.getChannel().close();
	                        this.channelTables.remove(addr);
	                        createNewConnection = true;
	                    } else if (!cw.getChannelFuture().isDone()) {
	                        createNewConnection = false;
	                    } else {
	                        this.channelTables.remove(addr);
	                        createNewConnection = true;
	                        try {
								cw.getChannel().close();
							} catch (Exception e) {
								//不处理
							}
	                    }
	                } else {
	                    createNewConnection = true;
	                }

	                if (createNewConnection) {
	                    ChannelFuture channelFuture = this.bootstrap.connect(NewsRpcUtil.string2SocketAddress(addr));
	                    LoggerUtils.info("createChannel: begin to connect remote host["+addr+"] asynchronously");
	                    cw = new ChannelWrapper(channelFuture);
	                    this.channelTables.put(addr, cw);
	                }
	            } catch (Exception e) {
	            	LoggerUtils.error("createChannel: create channel exception", e);
	            } finally {
	                this.lockChannelTables.unlock();
	            }
	        } else {
	        	LoggerUtils.warn("createChannel: try to lock channel table, but timeout, "+LOCK_TIMEOUT_MILLIS+"ms");
	        }

	        if (cw != null) {
	            ChannelFuture channelFuture = cw.getChannelFuture();
	            if (channelFuture.awaitUninterruptibly(CONNECT_TIMEOUT)) {
	                if (cw.isOK()) {
	                	LoggerUtils.info("createChannel: connect remote host["+addr+"] success, "+channelFuture.toString());
	                    return cw.getChannel();
	                } else {
	                	LoggerUtils.warn("createChannel: connect remote host[" + addr + "] failed, " + channelFuture.toString(), channelFuture.cause());
	                }
	            } else {
	            	LoggerUtils.warn("createChannel: connect remote host["+addr+"] timeout "+CONNECT_TIMEOUT+"ms, "+channelFuture.toString());
	            }
	        }

	        return null;
	    }

    
	public static NettyRpcClient getInstance(){
		NotifyListener notifyListener = InterfaceExtensionLoader.getExtensionLoader(NotifyListener.class).getExtension("netty_rpc_client");
		NettyRpcClient nettyRpcClient = (NettyRpcClient) notifyListener;
		if(nettyRpcClient == null){
			nettyRpcClient = new NettyRpcClient();
		}
		return nettyRpcClient;
	}

	@Override
	protected Set<String> getCategory() {
		Set<String> category = new HashSet<>();
		category.add(NewsConstants.ZOOKEEPER_NODE_REMOVED_EVENT);
		return category;
	}

	@Override
	protected void doNotify(URL url) throws NewsRpcException {
		//暂时不处理任何事情
	}
	
	public Bootstrap getBootstrap() {
		return bootstrap;
	}

	/**
	 * Channel 包装器
	 * @author wulin
	 *
	 */
	static class ChannelWrapper {
        private final ChannelFuture channelFuture;

        public ChannelWrapper(ChannelFuture channelFuture) {
            this.channelFuture = channelFuture;
        }

        public boolean isOK() {
            return this.channelFuture.channel() != null && this.channelFuture.channel().isActive();
        }

        public boolean isWritable() {
            return this.channelFuture.channel().isWritable();
        }

        private Channel getChannel() {
            return this.channelFuture.channel();
        }

        public ChannelFuture getChannelFuture() {
            return channelFuture;
        }
    }
}
