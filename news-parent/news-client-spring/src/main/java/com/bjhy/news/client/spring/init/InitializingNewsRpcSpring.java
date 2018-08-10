package com.bjhy.news.client.spring.init;

import org.springframework.context.ApplicationContext;

/**
 * 代替 spring InitializingBean,用于程序启动处理一些应用业务
 * @author wubo
 */
public interface InitializingNewsRpcSpring {
	
	void init(ApplicationContext applicationContext) throws Exception;

}
