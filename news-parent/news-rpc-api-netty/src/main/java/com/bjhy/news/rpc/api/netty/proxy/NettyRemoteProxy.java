package com.bjhy.news.rpc.api.netty.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bjhy.news.common.api.InvokerStrategyCluster;
import com.bjhy.news.common.domain.DiscoveryServiceDetailInfo;
import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.proxy.RemoteProxy;
import com.bjhy.news.rpc.api.netty.domain.RpcRequest;
import com.bjhy.news.rpc.api.netty.domain.RpcResponse;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import cn.wulin.ioc.util.UrlUtils;
@SuppressWarnings("unused")
public class NettyRemoteProxy implements RemoteProxy {
	
	private Logger logger = LoggerFactory.getLogger(NettyRemoteProxy.class);


	@Override
	public <T> T remoteInvoke(URL url, DiscoveryServiceInfo discoveryServiceInfo, Class<T> clazz) {
		return create(url,clazz);
	}

	@SuppressWarnings("unchecked")
	private <T> T create(URL url,final Class<?> interfaceClass) {
		// 创建动态代理对象
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] { interfaceClass },
			new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					URL cobyUrl = UrlUtils.parseURL(url.toFullString(), null);
					cobyUrl = cobyUrl.addParameter("method", method.getName());
					
					InvokerStrategyCluster adaptiveExtension = InterfaceExtensionLoader.getExtensionLoader(InvokerStrategyCluster.class).getAdaptiveExtension();
					return adaptiveExtension.invoke(url, interfaceClass, proxy, method, args);
			    }
		});
	}
}
