package com.bjhy.news.cluster.loadbalance.support;

import com.bjhy.news.common.api.LoadBalance;
import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.exception.NewsRpcException;
import com.bjhy.news.common.util.NewsConstants;
import com.bjhy.news.common.zookeeper.DiscoveryZkService;

import cn.wulin.ioc.URL;

/**
 * 负载均衡的抽象类
 * @author wubo
 *
 */
public abstract class AbstractLoadBalance implements LoadBalance{

	@Override
	public DiscoveryServiceInfo select(URL url,DiscoveryServiceInfo selected) throws NewsRpcException {
		return doSelect(url,selected);
	}
	
	/**
	 * 得到所有的提供者服务
	 * @param url
	 * @return
	 * @throws NewsRpcException
	 */
	protected DiscoveryServiceInfo list(URL url,DiscoveryServiceInfo willBeDeleted) throws NewsRpcException {
		Class<?> interfaceClass;
		try {
			interfaceClass = Class.forName(url.getParameter(NewsConstants.SERVICE_INTERFACE_KEY));
		} catch (ClassNotFoundException e) {
			throw new NewsRpcException(e.getMessage(),e);
		}
		
		DiscoveryServiceInfo allProviders = DiscoveryZkService.getInstance().subscribeService(
				url.getParameter(NewsConstants.CLIENT_TOPIC_KEY),
				url.getParameter(NewsConstants.CLIENT_TAG_KEY),
				interfaceClass, 
				url.getParameter(NewsConstants.SYNC_VERSION_KEY),
				willBeDeleted);
		return allProviders;
	}
	
	/**
	 * 根据负载均衡策略选择合适的一个提供者
	 * @param url
	 * @param selected 已经被选中过的提供者
	 * @return
	 */
	protected abstract DiscoveryServiceInfo doSelect(URL url,DiscoveryServiceInfo selected) throws NewsRpcException;
}
