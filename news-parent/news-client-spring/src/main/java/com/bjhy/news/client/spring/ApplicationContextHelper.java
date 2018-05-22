package com.bjhy.news.client.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.bjhy.cache.toolkit.extension.ExtensionLoader;
import com.bjhy.news.client.spring.connect.SpringLoadNewsConnect;
import com.bjhy.news.common.connect.AdaptiveNewsConnect;
import com.bjhy.news.common.connect.ConnectConfig;
import com.bjhy.news.common.init.AdaptiveNewsInitializing;
import com.bjhy.news.common.zookeeper.AdaptivePublishService;
import com.bjhy.news.rpc.api.netty.AdaptiveRpcInvokeService;

import cn.wulin.ioc.URL;

@Component
public class ApplicationContextHelper implements ApplicationContextAware ,InitializingBean{
	
	private static transient ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextHelper.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		AdaptiveNewsConnect.setDefaultAdaptiveNewsConnect("spring.news.connect");
		AdaptivePublishService.setDefaultAdaptivePublishService("spring.publish.service");
		AdaptiveRpcInvokeService.setDefaultRpcInvokeService("netty.rpc.invoke.service");
		AdaptiveNewsInitializing.setDefaultAdaptiveNewsInitializing("spring.news.initializing");
		
		//之所将该对象从spring的applicationContext中取出来存储在 ExtensionLoader 中,是为避免 子线程访问 spring 的applicationContext 报 java.util.ConcurrentModificationException 异常
		ExtensionLoader.setInstance(SpringLoadNewsConnect.class, ApplicationContextHelper.getApplicationContext().getBean(SpringLoadNewsConnect.class));
		
		Map<String,String> params = new HashMap<String,String>();
		URL url = new URL("initializing", "0.0.0.0", 0, params);
		ConnectConfig.getInstance().connectEntry(url);
	}
	
}
