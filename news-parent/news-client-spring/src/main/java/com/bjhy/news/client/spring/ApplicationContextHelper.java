package com.bjhy.news.client.spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.bjhy.news.client.spring.connect.SpringLoadNewsConnect;
import com.bjhy.news.client.spring.init.InitializingNewsRpcSpring;
import com.bjhy.news.common.connect.AdaptiveNewsConnect;
import com.bjhy.news.common.connect.ConnectConfig;
import com.bjhy.news.common.init.AdaptiveNewsInitializing;
import com.bjhy.news.common.zookeeper.AdaptivePublishService;
import com.bjhy.news.rpc.api.netty.AdaptiveRpcInvokeService;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.ExtensionLoader;

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
		
		//调用InitializingNewsRpcSpring,代替 spring InitializingBean,用于程序启动处理一些应用业务
		callInitializingNewsRpcSpring();
	}
	
	/**
	 * 调用InitializingNewsRpcSpring
	 * @throws Exception 
	 */
	private void callInitializingNewsRpcSpring() throws Exception{
		Map<String, InitializingNewsRpcSpring> beansOfType = applicationContext.getBeansOfType(InitializingNewsRpcSpring.class);
		if(beansOfType != null){
			Collection<InitializingNewsRpcSpring> InitializingNewsRpcSpringCollection = beansOfType.values();
			for (InitializingNewsRpcSpring initializingNewsRpcSpring : InitializingNewsRpcSpringCollection) {
				initializingNewsRpcSpring.init(applicationContext);
			}
		}
	}
	
}
