package com.bjhy.news.rpc.api.netty.handler;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import com.bjhy.news.common.domain.PublishServiceInfo;
import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.domain.RpcResponse;

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
	
	public RpcServerHandler(List<PublishServiceInfo> publishServiceInfoList){
		this.publishServiceInfoList = publishServiceInfoList;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcRequest request) throws Exception {
		RpcServerHandler.submit(new Runnable() {
            @Override
            public void run() {
            	 // 创建并初始化 RPC 响应对象
                logger.debug("Receive request " + request.getRequestId());
                RpcResponse response = new RpcResponse();
                response.setRequestId(request.getRequestId());
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
    	logger.error("server caught exception", cause);
        ctx.close();
    }

}
