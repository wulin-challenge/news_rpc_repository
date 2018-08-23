package com.bjhy.news.cluster.support;

import java.lang.reflect.Method;

import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.exception.NewsRpcException;
import com.bjhy.news.common.util.NewsConstants;
import cn.wulin.ioc.URL;

/**
 * 调用时 失败重试策略
 * 
 * @author wubo
 *
 */
public class FailoverInvokerStrategy extends AbstractInvokerStrategyCluster {

	@Override
	public Object doInvoke(URL url, Class<?> interfaceClass,Object proxy, Method method, Object[] args) throws NewsRpcException {
		NewsRpcException lastException = null; // 最后一次执行失败的异常
		// 被选择过的服务提供者
		DiscoveryServiceInfo origin = addSelected(url, null, null, interfaceClass);
		int retries = url.getParameter(NewsConstants.RETRIES_KEY, 2) + 1;
		if (retries <= 0) {
			retries = 1;
		}
		for (int i = 0; i < retries; i++) {
			DiscoveryServiceInfo selected = select(url, origin);
			try {
				if(selected == null || selected.getDiscoveryServiceDetailInfoList().size() ==0){
					lastException = new NewsRpcException("没有找到可用的提供者!");
					continue;
				}
				return remoteInvoke(url, interfaceClass, selected, proxy, method, args);
			} catch (NewsRpcException e) {
				if (e.isBiz()) { // 业务异常直接抛出
					throw e;
				}
				lastException = e;
			} catch (Throwable e) {
				lastException = new NewsRpcException(e.getMessage(), e);
			} finally {
				origin = addSelected(url, selected, origin, interfaceClass);
			}
		}
		throw new NewsRpcException(retries+"次尝试调用失败!,服务接口:"+interfaceClass.getName()+",方法:"+method.getName(),lastException);
	}

}
