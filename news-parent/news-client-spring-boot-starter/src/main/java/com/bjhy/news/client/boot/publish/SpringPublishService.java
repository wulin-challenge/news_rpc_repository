package com.bjhy.news.client.boot.publish;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.cglib.proxy.Proxy;

import com.bjhy.news.client.boot.NewsBootConfigurer;
import com.bjhy.news.client.boot.proxy.NewsInvocationHandler;
import com.bjhy.news.common.annotation.NewsListenerService;
import com.bjhy.news.common.domain.PublishServiceInfo;
import com.bjhy.news.common.zookeeper.PublishService;

import cn.wulin.ioc.extension.ExtensionLoader;

/**
 * spring boot实现
 * @author wulin
 *
 */
public class SpringPublishService implements PublishService{
	
	private ConfigurableListableBeanFactory beanFactory;
	
	private NewsBootConfigurer bewsBootConfigurer;

	public SpringPublishService() {
		super();
		bewsBootConfigurer = ExtensionLoader.getInstance(NewsBootConfigurer.class);
		beanFactory = bewsBootConfigurer.getBeanFactory();
	}

	@Override
	public List<PublishServiceInfo> getPublishServiceInfo() {
		
		List<PublishServiceInfo> spiList = new ArrayList<>();
		String[] beanNames = beanFactory.getBeanNamesForAnnotation(NewsListenerService.class);
		
	    if(beanNames == null) {
	    	return spiList;
	    }
		
	    for (String beanName : beanNames) {
	    	PublishServiceInfo publishServiceInfo = getPublishServiceInfo(beanName);
	    	spiList.add(publishServiceInfo);
		}
		return spiList;
	}
	
	private PublishServiceInfo getPublishServiceInfo(String beanName) {
		
		PublishServiceInfo publishServiceInfo = new PublishServiceInfo();
		
		Class<?> beanClass = beanFactory.getType(beanName);
    	NewsListenerService newsListenerService = beanClass.getAnnotation(NewsListenerService.class);
    	
        String serviceVersion = newsListenerService.syncVersion();
        int syncTimeout = newsListenerService.syncTimeout();
       
        if (StringUtils.isNoneBlank(serviceVersion)) {
        	publishServiceInfo.setSyncVersion(serviceVersion);
        }
        
        ClassLoader classLoader = bewsBootConfigurer.getClassLoader();
        Object beanProxy = Proxy.newProxyInstance(classLoader, new Class[] {newsListenerService.value()}, new NewsInvocationHandler(beanName, beanClass,beanFactory));
       
        publishServiceInfo.setServiceClass(newsListenerService.value());
        publishServiceInfo.setSyncTimeout(syncTimeout);
        publishServiceInfo.setServiceImplObject(beanProxy);
		return publishServiceInfo;
	}

}
