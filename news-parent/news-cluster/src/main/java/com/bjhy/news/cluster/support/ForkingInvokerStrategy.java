package com.bjhy.news.cluster.support;

import java.lang.reflect.Method;

import com.bjhy.news.common.exception.NewsRpcException;

import cn.wulin.ioc.URL;

/**
 * 调用时 并行调用策略
 * @author wubo
 */
public class ForkingInvokerStrategy extends AbstractInvokerStrategyCluster{

	@Override
	public Object doInvoke(URL url, Class<?> interfaceClass,Object proxy, Method method, Object[] args) throws NewsRpcException {
		return null;
	}


}
