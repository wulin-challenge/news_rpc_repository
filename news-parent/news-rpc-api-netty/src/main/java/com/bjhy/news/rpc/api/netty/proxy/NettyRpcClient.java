package com.bjhy.news.rpc.api.netty.proxy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bjhy.news.common.proxy.RemoveRpcClient;
import com.bjhy.news.rpc.api.netty.codec.RpcDecoder;
import com.bjhy.news.rpc.api.netty.codec.RpcEncoder;
import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.domain.RpcResponse;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * RPC 客户端（用于发送 RPC 请求）
 * @author wubo
 */
public class NettyRpcClient implements RemoveRpcClient{
	
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyRpcClient.class);
    
    private static ConcurrentHashMap<String, NettyRpcClientHandler> cacheConnect = new ConcurrentHashMap<>();
    
    public NettyRpcClientHandler connectServer(RpcRequest request) {
    	String address = request.getHost() + "_" + request.getPort();
    	if(!NettyRpcClient.cacheConnect.containsKey(address)){
    		connectServer(request.getHost(), request.getPort());
    	}
    	return NettyRpcClient.cacheConnect.get(address);
    }
    
	private void connectServer(String host, Integer port) {
		CountDownLatch count = new CountDownLatch(1);
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup(4);
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>(){
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						ChannelPipeline cp = socketChannel.pipeline();
				        cp.addLast(new RpcEncoder(RpcRequest.class));
				        cp.addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 0));
				        cp.addLast(new RpcDecoder(RpcResponse.class));
				        cp.addLast(new NettyRpcClientHandler());
					}
                });
        
		ChannelFuture channelFuture = bootstrap.connect(host, port);
		channelFuture.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(final ChannelFuture channelFuture) throws Exception {
				if (channelFuture.isSuccess()) {
					LOGGER.debug("Successfully connect to remote server. remote peer = " + host + ":" + port);
					NettyRpcClientHandler handler = channelFuture.channel().pipeline().get(NettyRpcClientHandler.class);
					cacheConnect.put(host + "_" + port, handler);
				}
				count.countDown();
			}
		});
		try {
			count.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
    
	public static NettyRpcClient getInstance(){
		RemoveRpcClient removeRpcClient = InterfaceExtensionLoader.getExtensionLoader(RemoveRpcClient.class).getExtension("netty_rpc_client");
		NettyRpcClient nettyRpcClient = (NettyRpcClient) removeRpcClient;
		if(nettyRpcClient == null){
			nettyRpcClient = new NettyRpcClient();
		}
		return nettyRpcClient;
	}

	@Override
	public void cleanRpcClient(URL url) {
		if(url != null){
			String address = url.getHost() + "_" + url.getPort();
			cacheConnect.remove(address);
		}
	}
}
