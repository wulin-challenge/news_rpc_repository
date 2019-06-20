package com.bjhy.news.client.boot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;

import com.bjhy.news.common.connect.AdaptiveNewsConnect;
import com.bjhy.news.common.connect.ConnectConfig;
import com.bjhy.news.common.zookeeper.AdaptivePublishService;
import com.bjhy.news.rpc.api.netty.AdaptiveRpcInvokeService;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.ExtensionLoader;

/**
 * news rpc集成spring boot 的配置类
 * @author wulin
 *
 */
public class NewsBootConfigurer implements EnvironmentAware,BeanFactoryAware,BeanFactoryPostProcessor,BeanClassLoaderAware, Ordered{
	private Environment environment;
	
	private DefaultListableBeanFactory beanFactory;
	
	private ClassLoader classLoader;
	
	private Map<String,String> params = new HashMap<String,String>();
	
	public NewsBootConfigurer() {
		super();
	}

	public NewsBootConfigurer(Map<String, String> params) {
		super();
		this.params.putAll(params);
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (DefaultListableBeanFactory) beanFactory;
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		ExtensionLoader.setInstance(NewsBootConfigurer.class, this);
		
		AdaptiveNewsConnect.setDefaultAdaptiveNewsConnect("spring.news.connect");
		AdaptivePublishService.setDefaultAdaptivePublishService("spring.publish.service");
		AdaptiveRpcInvokeService.setDefaultRpcInvokeService("netty.rpc.invoke.service");
		
		URL url = new URL("initializing", "0.0.0.0", 0, params);
		ConnectConfig.getInstance().connectEntry(url);
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public DefaultListableBeanFactory getBeanFactory() {
		return beanFactory;
	}

	public ClassLoader getClassLoader() {
		return classLoader;
	}
}
