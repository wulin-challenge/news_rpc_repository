package com.bjhy.news.rpc.api.netty.proxy;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bjhy.news.common.heartbeat.telnet.TelnetHeartbeatInfo;
import com.bjhy.news.common.util.NewsRpcUtil;
import com.bjhy.news.rpc.api.netty.domain.NettyRpcType;
import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.domain.RpcResponse;

import cn.wulin.brace.utils.LoggerUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 如果此handler不共享(@Sharable),那么在
 * <pre>
 *  ChannelFuture channelFuture = this.bootstrap.connect(NewsRpcUtil.string2SocketAddress(addr));
 * <pre>
 * 
 * 时,将报 此handler 不是 @Sharable
 * @author wulin
 *
 */
@Sharable
public class NettyRpcClientHandler extends SimpleChannelInboundHandler<RpcResponse> {

	private ConcurrentHashMap<String, RPCFuture> pendingRPC = new ConcurrentHashMap<>();
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
		if(NettyRpcType.TELNET_SERVICE == response.getRpcType()) {
			TelnetHeartbeatInfo info =  (TelnetHeartbeatInfo)response.getResult();
			if(info.getRequestCode() == 0) {
				dealWithTelnetResponse(ctx, response);
				return;
			}
		}
		String requestId = response.getRequestId();
		RPCFuture rpcFuture = pendingRPC.get(requestId);
		if (rpcFuture != null) {
			pendingRPC.remove(requestId);
			rpcFuture.done(response);
		}
	}
	
	/**
	 * 处理来自服务端telnet的响应
	 * @param ctx
	 * @param response
	 */
	private void dealWithTelnetResponse(ChannelHandlerContext ctx, RpcResponse response) {
		HeartbeatHandler.getInstance().addRemoteHeartbeat(ctx, response);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		LoggerUtils.error(cause.getMessage());
	}

	 public RPCFuture sendRequest(Channel channel,RpcRequest request) {
	        final CountDownLatch latch = new CountDownLatch(1);
	        RPCFuture rpcFuture = new RPCFuture(request);
	        pendingRPC.put(request.getRequestId(), rpcFuture);
	        channel.writeAndFlush(request).addListener(new ChannelFutureListener() {
	            @Override
	            public void operationComplete(ChannelFuture future) {
	                latch.countDown();
	            }
	        });
	        try {
	            latch.await();
	        } catch (InterruptedException e) {
	            LoggerUtils.error(e.getMessage());
	        }
	        return rpcFuture;
	    }

}
