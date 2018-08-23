package com.bjhy.news.common.api;

import java.lang.reflect.Method;

import com.bjhy.news.common.exception.NewsRpcException;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.Adaptive;
import cn.wulin.ioc.extension.SPI;

/**
 * 调用策略集群(模仿dubbo中的cluster)
 * @author wubo
 */
@SPI("failover")
public interface InvokerStrategyCluster {

	/**
	 * 真正的调用
	 * @param url 
	 * @param interfaceClass 服务接口的class
	 * @param proxy 代理对象
	 * @param method 调用方法
	 * @param args 方法参数
	 * @return
	 * @throws NewsRpcException
	 */
	@Adaptive("cluster")
	Object invoke(URL url,Class<?> interfaceClass,Object proxy, Method method, Object[] args) throws NewsRpcException;
}
