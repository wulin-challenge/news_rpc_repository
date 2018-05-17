package com.bjhy.news.common.proxy;

import com.bjhy.news.common.domain.DiscoveryServiceInfo;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.Adaptive;
import cn.wulin.ioc.extension.SPI;

@SPI
public interface RemoteProxy {
	
	/**
	 * @param url
	 * @param discoveryServiceInfo 发现服务信息
	 */
	@Adaptive("invokeType")
	<T> T remoteInvoke(URL url,DiscoveryServiceInfo discoveryServiceInfo,Class<T> clazz);

}
