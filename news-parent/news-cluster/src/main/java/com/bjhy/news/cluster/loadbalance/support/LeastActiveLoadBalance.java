package com.bjhy.news.cluster.loadbalance.support;

import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.exception.NewsRpcException;

import cn.wulin.ioc.URL;

/**
 * 最小活动数策略
 * @author wubo
 *
 */
public class LeastActiveLoadBalance extends AbstractLoadBalance{

	@Override
	protected DiscoveryServiceInfo doSelect(URL url, DiscoveryServiceInfo selected) throws NewsRpcException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
