package com.bjhy.news.cluster.support;

import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.bjhy.news.common.api.InvokerStrategyCluster;
import com.bjhy.news.common.api.LoadBalance;
import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.domain.DiscoveryServiceDetailInfo;
import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.exception.NewsRpcException;
import com.bjhy.news.common.util.NewsConstants;
import com.bjhy.news.common.util.NewsRpcUtil;
import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.domain.RpcResponse;
import com.bjhy.news.rpc.api.netty.proxy.NettyRpcClient;
import com.bjhy.news.rpc.api.netty.proxy.RPCFuture;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import io.netty.channel.Channel;

public abstract class AbstractInvokerStrategyCluster implements InvokerStrategyCluster{
	
	/**
	 * 得到连接配置信息
	 */
	private NewsConnect newsConnect = InterfaceExtensionLoader.getExtensionLoader(NewsConnect.class).getAdaptiveExtension();
	

	@Override
	public Object invoke(URL url,Class<?> interfaceClass,Object proxy, Method method, Object[] args)throws NewsRpcException {
		return doInvoke(url, interfaceClass,proxy,method,args);
	}

	public abstract Object doInvoke(URL url,Class<?> interfaceClass,Object proxy, Method method, Object[] args)throws NewsRpcException;
	
	/**
	 * 选择出一个服务提供者
	 * @param url
	 * @param selected 被选择过的服务提供者
	 * @return
	 */
	protected DiscoveryServiceInfo select(URL url,DiscoveryServiceInfo selected){
		LoadBalance loadBalance = InterfaceExtensionLoader.getExtensionLoader(LoadBalance.class).getAdaptiveExtension();
		DiscoveryServiceInfo select = loadBalance.select(url, selected);
		return select;
	}
	
	/**
	 * 添加被选择的服务提供者
	 * @param url
	 * @param selected 被选中的服务提供者
	 * @param origin 原来已经被选择过的服务提供者
	 * @param interfaceClass 服务接口
	 * @return
	 */
	protected DiscoveryServiceInfo addSelected(URL url,DiscoveryServiceInfo selected,DiscoveryServiceInfo origin,Class<?> interfaceClass){
		
		if(origin == null){
			origin = new DiscoveryServiceInfo();
			origin.setClientTopic(url.getParameter(NewsConstants.CLIENT_TOPIC_KEY));
			origin.setClientTag(url.getParameter(NewsConstants.CLIENT_TAG_KEY));
			origin.setServiceClass(interfaceClass);
		}
		if(selected != null && selected.getDiscoveryServiceDetailInfoList().size()>0 ){
			origin.getDiscoveryServiceDetailInfoList().add(selected.getDiscoveryServiceDetailInfoList().get(0));
		}
		return origin;
	}
	
	/**
	 * 执行远程条用
	 * @param url
	 * @param selected 被选中的服务提供者
	 * @param interfaceClass 服务接口
	 * @return
	 */
	protected Object remoteInvoke(URL url,Class<?> interfaceClass,DiscoveryServiceInfo selected,Object proxy, Method method, Object[] args){
		DiscoveryServiceDetailInfo detailInfo = selected.getDiscoveryServiceDetailInfoList().get(0);
		
		Class<?> returnType = method.getReturnType();
		// 创建 RPC 请求对象并设置请求属性
		RpcRequest request = new RpcRequest();
		request.setRequestId(UUID.randomUUID().toString());
		request.setInterfaceName(method.getDeclaringClass().getName());
		request.setServiceVersion(detailInfo.getVersion());
		request.setMethodName(method.getName());
		request.setParameterTypes(method.getParameterTypes());
		request.setParameters(args);
		request.setTimeout(detailInfo.getTimeout());
		String host = detailInfo.getServiceIp();
		int port = detailInfo.getServicePort();
		request.setHost(host);
		request.setPort(port);
		request.setClientId(newsConnect.clientId());
		request.setClientName(newsConnect.clientName());
		request.setClientPid(NewsRpcUtil.getPid());
		
		// 创建 RPC 客户端对象并发送 RPC 请求
		Channel channel = NettyRpcClient.getInstance().getChannel(request);
		RPCFuture sendRequest = NettyRpcClient.getInstance().getClientHandler().sendRequest(channel,request);
		
		Integer timeout = url.getParameter(NewsConstants.SYNC_TIMEOUT_KEY,0);
		timeout = timeout <= 0?((request.getTimeout() ==null || request.getTimeout()<=0)?NewsConstants.DEFUALT_SYNC_TIMEOUT:request.getTimeout()):timeout;
		Object object;
		try {
			object = sendRequest.get(timeout, TimeUnit.MILLISECONDS);
		} catch (NewsRpcException|InterruptedException | ExecutionException | TimeoutException e) {
			throw new NewsRpcException(e);
		}
		if(object != null){
			RpcResponse rpcResponse = (com.bjhy.news.rpc.api.netty.domain.RpcResponse) object;
			Object result = rpcResponse.getResult();
			if(result != null || returnType == void.class){
				return result;
			}else {
				throw new NewsRpcException(rpcResponse.getException());
			}
		}
		return null;
	}
}
