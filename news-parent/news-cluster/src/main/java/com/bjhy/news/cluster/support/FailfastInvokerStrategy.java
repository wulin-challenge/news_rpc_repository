package com.bjhy.news.cluster.support;

import java.lang.reflect.Method;

import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.exception.NewsRpcException;

import cn.wulin.ioc.URL;

/**
 * 调用时 快速失败策略
 * @author wubo
 *
 */
public class FailfastInvokerStrategy extends AbstractInvokerStrategyCluster{

	@Override
	public Object doInvoke(URL url, Class<?> interfaceClass,Object proxy, Method method, Object[] args) throws NewsRpcException {
		// 被选择过的服务提供者
		DiscoveryServiceInfo origin = addSelected(url, null, null, interfaceClass);
		DiscoveryServiceInfo selected = select(url, origin);
		try {
			if(selected == null || selected.getDiscoveryServiceDetailInfoList().size() ==0){
				throw new NewsRpcException("没有找到可用的提供者!");
			}
			return remoteInvoke(url, interfaceClass, selected, proxy, method, args);
		}catch (Throwable e) {
			throw new NewsRpcException(e.getMessage(), e);
		}
	}
}
