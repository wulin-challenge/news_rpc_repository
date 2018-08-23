package com.bjhy.news.cluster.loadbalance.support;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.bjhy.news.common.domain.DiscoveryServiceDetailInfo;
import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.exception.NewsRpcException;

import cn.wulin.ioc.URL;

/**
 * 随机负载均衡策略
 * @author wubo
 *
 */
public class RandomLoadBalance extends AbstractLoadBalance{

	@Override
	protected DiscoveryServiceInfo doSelect(URL url, DiscoveryServiceInfo selected) throws NewsRpcException {
		//得到所有的提供者服务
		DiscoveryServiceInfo list = list(url,selected);
		List<DiscoveryServiceDetailInfo> discoveryServiceDetailInfoList = list.getDiscoveryServiceDetailInfoList();
		
		if(discoveryServiceDetailInfoList == null || discoveryServiceDetailInfoList.isEmpty()){
			return null;
		}
		
		int size = discoveryServiceDetailInfoList.size();
		if(size ==1){
			return list;
		}
		
		if(size>1){
			DiscoveryServiceDetailInfo discoveryServiceDetailInfo = discoveryServiceDetailInfoList.get(ThreadLocalRandom.current().nextInt(size));
			//拷贝一份新服务列表
			return copyNewDiscoveryServiceInfo(list, discoveryServiceDetailInfo);
		}
		return null;
	}

	/**
	 * 拷贝一份新服务列表
	 * @param list
	 * @param discoveryServiceDetailInfo
	 * @return
	 */
	private DiscoveryServiceInfo copyNewDiscoveryServiceInfo(DiscoveryServiceInfo list,DiscoveryServiceDetailInfo discoveryServiceDetailInfo) {
		//拷贝一份新的服务
		DiscoveryServiceInfo onlyOneDiscoveryServiceInfo = new DiscoveryServiceInfo();
		onlyOneDiscoveryServiceInfo.setClientTopic(list.getClientTopic());
		onlyOneDiscoveryServiceInfo.setClientTag(list.getClientTag());
		onlyOneDiscoveryServiceInfo.setServiceClass(list.getServiceClass());
		onlyOneDiscoveryServiceInfo.getDiscoveryServiceDetailInfoList().add(discoveryServiceDetailInfo);
		
		return onlyOneDiscoveryServiceInfo;
	}

}
