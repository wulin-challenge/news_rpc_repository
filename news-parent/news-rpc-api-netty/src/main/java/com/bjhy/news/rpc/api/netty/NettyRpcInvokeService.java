package com.bjhy.news.rpc.api.netty;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.ProviderConsumerType;
import com.bjhy.news.common.domain.PublishServiceInfo;
import com.bjhy.news.common.service.RpcInvokeService;
import com.bjhy.news.rpc.api.netty.codec.RpcDecoder;
import com.bjhy.news.rpc.api.netty.codec.RpcEncoder;
import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.domain.RpcResponse;
import com.bjhy.news.rpc.api.netty.handler.RpcServerHandler;

import cn.wulin.brace.remoting.RemotingException;
import cn.wulin.brace.remoting.exchange.ExchangeHandler;
import cn.wulin.brace.telnet.TelnetConstants;
import cn.wulin.brace.telnet.TelnetExchangeHandler;
import cn.wulin.brace.telnet.TelnetServers;
import cn.wulin.brace.utils.LoggerUtils;
import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 启动netty server端
 * @author wulin
 *
 */
public class NettyRpcInvokeService implements RpcInvokeService{
	/**
	 * telnet开启标识
	 */
	private boolean telnetStart = false;
	/**
	 * 得到连接配置信息
	 */
	private NewsConnect newsConnect = InterfaceExtensionLoader.getExtensionLoader(NewsConnect.class).getAdaptiveExtension();

	@Override
	public void executeRpc(List<PublishServiceInfo> publishServiceInfoList) {
		if(publishServiceInfoList.isEmpty()) {
			
			ProviderConsumerType providerConsumerType = ProviderConsumerType.getProviderConsumerTypeByCode(newsConnect.providerConsumer().trim());
			if(ProviderConsumerType.PROVIDER == providerConsumerType || ProviderConsumerType.BOTH == providerConsumerType) {
				//启动telnet服务
	            startTelnetServer();
			}
			LoggerUtils.warn("当前应用没有发布相关news远程服务,将只作为消费端使用!");
			return;
		}
		EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建并初始化 Netty 服务端 Bootstrap 对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast(new RpcDecoder(RpcRequest.class)); // 解码 RPC 请求
                    pipeline.addLast(new RpcEncoder(RpcResponse.class)); // 编码 RPC 响应
                    pipeline.addLast(new RpcServerHandler(publishServiceInfoList)); // 处理 RPC 请求
                }
            });
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            // 启动 RPC 服务器
            ChannelFuture future = bootstrap.bind(newsConnect.clientIp(), newsConnect.clientPort()).sync();
            LoggerUtils.info("news远程服务启动成功! 端口: "+newsConnect.clientPort());
            
            //启动telnet服务
            startTelnetServer();
            // 关闭 RPC 服务器
            future.channel().closeFuture().sync();
            
        } catch (Exception e) {
			LoggerUtils.error("news远程服务启动失败",e);
		} finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
	
	/**
	 * 启动telnet服务
	 */
	public void startTelnetServer() {
		if(telnetStart) {
			return;
		}
		
		LoggerUtils.info("正在启动telnet服务: 端口: "+newsConnect.clientTelnetPort());
		
		URL url = new URL(TelnetConstants.TELNET_PROTOCOL_KEY, newsConnect.clientIp(), newsConnect.clientTelnetPort());
		
		ExchangeHandler handler= new TelnetExchangeHandler();
		
		try {
			TelnetServers.bind(url, handler);
			telnetStart = true;
		} catch (RemotingException e) {
			throw new RuntimeException(e);
		}
	}
}
