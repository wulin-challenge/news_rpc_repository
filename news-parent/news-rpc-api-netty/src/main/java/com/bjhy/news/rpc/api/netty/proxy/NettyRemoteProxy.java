package com.bjhy.news.rpc.api.netty.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bjhy.news.common.domain.DiscoveryServiceDetailInfo;
import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.proxy.RemoteProxy;
import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.domain.RpcResponse;

import cn.wulin.ioc.URL;
@SuppressWarnings("unused")
public class NettyRemoteProxy implements RemoteProxy {
	
	private Logger logger = LoggerFactory.getLogger(NettyRemoteProxy.class);


	@Override
	public <T> T remoteInvoke(URL url, DiscoveryServiceInfo discoveryServiceInfo, Class<T> clazz) {
		return create(clazz, discoveryServiceInfo);
	}

	@SuppressWarnings("unchecked")
	private <T> T create(final Class<?> interfaceClass, final DiscoveryServiceInfo discoveryServiceInfo) {
		// 创建动态代理对象
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] { interfaceClass },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						DiscoveryServiceDetailInfo detailInfo = discoveryServiceInfo.getDiscoveryServiceDetailInfoList().get(0);
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
						// 创建 RPC 客户端对象并发送 RPC 请求
						NettyRpcClientHandler connectServer = NettyRpcClient.getInstance().connectServer(request);
						RPCFuture sendRequest = connectServer.sendRequest(request);
						Object object = sendRequest.get(request.getTimeout(), TimeUnit.MILLISECONDS);
						if(object != null){
							RpcResponse rpcResponse = (com.bjhy.news.rpc.api.netty.domain.RpcResponse) object;
							Object result = rpcResponse.getResult();
							if(result != null){
								return result;
							}else {
								throw rpcResponse.getException();
							}
						}
						return null;
					}
				});
	}

}
