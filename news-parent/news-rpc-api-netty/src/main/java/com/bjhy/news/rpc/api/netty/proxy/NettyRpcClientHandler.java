package com.bjhy.news.rpc.api.netty.proxy;

import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.domain.RpcResponse;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyRpcClientHandler extends SimpleChannelInboundHandler<RpcResponse> {

	private static final Logger logger = LoggerFactory.getLogger(NettyRpcClientHandler.class);

	private ConcurrentHashMap<String, RPCFuture> pendingRPC = new ConcurrentHashMap<>();

	private volatile Channel channel;
	private SocketAddress remotePeer;

	public Channel getChannel() {
		return channel;
	}

	public SocketAddress getRemotePeer() {
		return remotePeer;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		this.remotePeer = this.channel.remoteAddress();
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
		this.channel = ctx.channel();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
		String requestId = response.getRequestId();
		RPCFuture rpcFuture = pendingRPC.get(requestId);
		if (rpcFuture != null) {
			pendingRPC.remove(requestId);
			rpcFuture.done(response);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// LOGGER.error("api caught exception", cause);
		ctx.close();
	}

	public void close() {
		channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}
	
	 public RPCFuture sendRequest(RpcRequest request) {
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
	            logger.error(e.getMessage());
	        }
	        return rpcFuture;
	    }

}