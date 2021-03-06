package com.bjhy.news.client.spring.publish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import com.bjhy.news.client.spring.ApplicationContextHelper;
import com.bjhy.news.common.annotation.NewsListenerService;
import com.bjhy.news.common.domain.PublishServiceInfo;
import com.bjhy.news.common.zookeeper.PublishService;

public class SpringPublishService implements PublishService{

	@Override
	public List<PublishServiceInfo> getPublishServiceInfo() {
		List<PublishServiceInfo> spiList = new ArrayList<>();
		
		ApplicationContext applicationContext = ApplicationContextHelper.getApplicationContext();
		Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(NewsListenerService.class);
        if (MapUtils.isNotEmpty(serviceBeanMap)) {
            for (Object serviceBean : serviceBeanMap.values()) {
            	PublishServiceInfo publishServiceInfo = new PublishServiceInfo();
            	
            	NewsListenerService newsListenerService = serviceBean.getClass().getAnnotation(NewsListenerService.class);
                String serviceVersion = newsListenerService.syncVersion();
                int syncTimeout = newsListenerService.syncTimeout();
                
                if (StringUtils.isNoneBlank(serviceVersion)) {
                	publishServiceInfo.setSyncVersion(serviceVersion);
                }
                
                publishServiceInfo.setServiceClass(newsListenerService.value());
                publishServiceInfo.setSyncTimeout(syncTimeout);
                publishServiceInfo.setServiceImplObject(serviceBean);
                spiList.add(publishServiceInfo);
            }
        }
		return spiList;
	}

}
