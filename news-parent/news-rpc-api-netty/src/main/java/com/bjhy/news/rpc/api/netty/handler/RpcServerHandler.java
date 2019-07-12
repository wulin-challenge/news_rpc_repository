package com.bjhy.news.rpc.api.netty.handler;

import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import com.bjhy.news.common.domain.PublishServiceInfo;
import com.bjhy.news.rpc.api.netty.domain.NettyRpcType;
import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.domain.RpcResponse;

import cn.wulin.brace.utils.LoggerUtils;
import cn.wulin.brace.utils.ThreadFactoryImpl;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RpcServerHandler extends SimpleChannelInboundHandler<RpcRequest>{
	private Logger logger = LoggerFactory.getLogger(RpcServerHandler.class);
	
	/**
	 * 发布的service信息
	 */
	private List<PublishServiceInfo> publishServiceInfoList;
	
	private static ThreadPoolExecutor threadPoolExecutor;
	
	private static ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryImpl("RpcServerHandler"));
	
	/**
	 * 客户端通道和地址
	 */
	private static ConcurrentHashMap<Channel,RpcRequest> clientChannel = new ConcurrentHashMap<Channel,RpcRequest>();
	
	public RpcServerHandler(List<PublishServiceInfo> publishServiceInfoList){
		this.publishServiceInfoList = publishServiceInfoList;
		startScheduled();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcRequest request) throws Exception {
		RpcServerHandler.submit(new Runnable() {
            @Override
            public void run() {
            	//添加客户端通道
            	addClientChannel(request, ctx.channel());
            	 // 创建并初始化 RPC 响应对象
                logger.debug("Receive request " + request.getRequestId());
                RpcResponse response = new RpcResponse();
                response.setRequestId(request.getRequestId());
                response.setRpcType(request.getRpcType());
                try {
                    Object result = handle(request);
                    response.setResult(result);
                } catch (Exception t) {
                	response.setException(t);
                    logger.error("RPC Server handle request error",t);
                }
                ctx.writeAndFlush(response).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        logger.debug("Send response for request " + request.getRequestId());
                    }
                });
            }
        });
	}
	
	 public static void submit(Runnable task) {
	        if (threadPoolExecutor == null) {
	            synchronized (RpcServerHandler.class) {
	                if (threadPoolExecutor == null) {
	                    threadPoolExecutor = new ThreadPoolExecutor(16, 16, 600L,
	                            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));
	                }
	            }
	        }
	        threadPoolExecutor.submit(task);
	    }
	
	/**
	 * 通过 服务名和 版本得到bean对象
	 * @param serviceName 服务名
	 * @param version 版本
	 * @return
	 */
	private Object getBeanObject(String serviceName,String version){
		for (PublishServiceInfo publishServiceInfo : publishServiceInfoList) {
			String name = publishServiceInfo.getServiceClass().getName();
			String syncVersion = publishServiceInfo.getSyncVersion();
			
			if(StringUtils.isBlank(version)){
				if(StringUtils.isBlank(syncVersion) && serviceName.equals(name)){
					return publishServiceInfo.getServiceImplObject();
				}
			}else{
				if(StringUtils.isNoneBlank(syncVersion) && version.equals(syncVersion) && serviceName.equals(name)){
					return publishServiceInfo.getServiceImplObject();
				}
			}
		}
		return null;
	}
	
    private Object handle(RpcRequest request) throws Exception {
        // 获取服务对象
        String serviceName = request.getInterfaceName();
        String serviceVersion = request.getServiceVersion();
        
        Object serviceBean = getBeanObject(serviceName, serviceVersion);
        if (serviceBean == null) {
            throw new RuntimeException(String.format("can not find service bean by key: %s", serviceName));
        }
        // 获取反射调用所需的参数
        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();
        // 使用 CGLib 执行反射调用
        FastClass serviceFastClass = FastClass.create(serviceClass);
        FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
        return serviceFastMethod.invoke(serviceBean, parameters);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	logger.debug("server caught exception", cause);
        ctx.close();
    }
    
    public static ConcurrentHashMap<Channel,RpcRequest> getClientChannel() {
		return clientChannel;
	}

	/**
     * 添加客户端通道
     * @param request
     * @param channel
     */
    private void addClientChannel(RpcRequest request,Channel channel) {
    	if(NettyRpcType.MOCK_SERVICE == request.getRpcType() && channel != null && channel.isActive()) {
    		clientChannel.put(channel, request);
    	}
    }
    
    /**
     * 开始定时器
     */
    private void startScheduled() {
    	scheduledExecutor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					checkChannel();
				} catch (Exception e) {
					LoggerUtils.error("清除过期的 Channel时报了: ",e);
				}
			}
		}, 5, 15, TimeUnit.SECONDS);
    }
    
    /**
     * 检测channel是否可用
     */
    private void checkChannel() {
    	Enumeration<Channel> keys = clientChannel.keys();
    	while(keys.hasMoreElements()) {
    		Channel channel = keys.nextElement();
    		if(channel != null && channel.isActive()) {
    			continue;
    		}
    		clientChannel.remove(channel);
    	}
    }
    
}
