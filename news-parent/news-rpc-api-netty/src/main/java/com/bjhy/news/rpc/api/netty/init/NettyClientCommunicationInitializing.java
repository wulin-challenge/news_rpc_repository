package com.bjhy.news.rpc.api.netty.init;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.init.ClientCommunicationInitializing;
import com.bjhy.news.rpc.api.netty.codec.RpcDecoder;
import com.bjhy.news.rpc.api.netty.codec.RpcEncoder;
import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.domain.RpcResponse;
import com.bjhy.news.rpc.api.netty.proxy.NettyRpcClientHandler;

import cn.wulin.ioc.URL;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClientCommunicationInitializing implements ClientCommunicationInitializing{
	
	
	@Override
	public void init(URL url, NewsConnect newsConnect) {
//		try {
//	           // 创建并初始化 Netty 客户端 Bootstrap 对象
//			   EventLoopGroup group = new NioEventLoopGroup();
//			   Bootstrap bootstrap = new Bootstrap();
//	           bootstrap.group(group);
//	           bootstrap.channel(NioSocketChannel.class);
//	           bootstrap.handler(new ChannelInitializer<SocketChannel>() {
//	               @Override
//	               public void initChannel(SocketChannel channel) throws Exception {
//	                   ChannelPipeline pipeline = channel.pipeline();
//	                   pipeline.addLast(new RpcEncoder(RpcRequest.class)); // 编码 RPC 请求
//	                   pipeline.addLast(new RpcDecoder(RpcResponse.class)); // 解码 RPC 响应
//	                   pipeline.addLast(new NettyRpcClientHandler()); // 处理 RPC 响应
//	               }
//	           });
//	           bootstrap.option(ChannelOption.TCP_NODELAY, true);
//		}finally {
//          group.shutdownGracefully();
//      }
	}
	
//   private RpcResponse sendLogic(RpcRequest request) throws InterruptedException{
//       
//           // 连接 RPC 服务器
//           ChannelFuture future = bootstrap.connect(request.getHost(), request.getPort()).sync();
//           // 写入 RPC 请求数据并关闭连接
//           Channel channel = future.channel();
//           channel.writeAndFlush(request).sync();
//           channel.closeFuture().sync();
//           // 返回 RPC 响应对象
//           return response;
//   }
}
