package com.bjhy.news.cluster.loadbalance.support;

import com.bjhy.news.common.api.LoadBalance;
import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.exception.NewsRpcException;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;

/**
 * c
 * 相同活跃数的随机，活跃数指调用前后计数差。
 * 使慢的提供者收到更少请求，因为越慢的提供者的调用前后计数差会越大。
 * @author wubo
 *
 */
public class LeastActiveLoadBalance extends AbstractLoadBalance{

	@Override
	//TODO 该算法暂未实现,暂时以RandomLoadBalance替代
	protected DiscoveryServiceInfo doSelect(URL url, DiscoveryServiceInfo selected) throws NewsRpcException {
		LoadBalance loadBalance = InterfaceExtensionLoader.getExtensionLoader(LoadBalance.class).getExtension("random");
		return loadBalance.select(url, selected);
	}

	

}
