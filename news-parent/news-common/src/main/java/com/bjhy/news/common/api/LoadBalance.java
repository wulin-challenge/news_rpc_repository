package com.bjhy.news.common.api;

import com.bjhy.news.common.domain.DiscoveryServiceInfo;
import com.bjhy.news.common.exception.NewsRpcException;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.Adaptive;
import cn.wulin.ioc.extension.SPI;

/**
 * 负载均衡接口
 * @author wubo
 *
 */
@SPI("ramdom")
public interface LoadBalance {
	
	/**
	 * 根据负载均衡策略选择合适的一个提供者
	 * @param url
	 * @param selected 已经被选中过的提供者
	 * @return
	 */
	@Adaptive({"loadbalance"})
	DiscoveryServiceInfo select(URL url,DiscoveryServiceInfo selected) throws NewsRpcException;

}
