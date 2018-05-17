package com.bjhy.news.example.test.publish.spring.service;

import java.io.IOException;
import java.util.List;

import org.apel.gaia.app.boot.AppStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bjhy.news.common.connect.ConnectConfig;
import com.bjhy.news.common.domain.PublishServiceInfo;
import com.bjhy.news.common.zookeeper.PublishService;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import cn.wulin.ioc.util.UrlUtils;

@SpringBootTest(classes=AppStarter.class)
@RunWith(SpringRunner.class)
public class TestPublishStart {
	
	@Test
	public void testSpringPublishService(){
		PublishService publishService = InterfaceExtensionLoader.getExtensionLoader(PublishService.class).getAdaptiveExtension();
		List<PublishServiceInfo> publishServiceInfo = publishService.getPublishServiceInfo();
		TestPublishService tps = (TestPublishService)publishServiceInfo.get(0).getServiceImplObject();
		tps.hello("world");
		String hello2 = tps.hello2("all");
		System.out.println(publishServiceInfo);
	}
	
	@Test
	public void testSpringPublishService_start(){
		URL emptyUrl = UrlUtils.getEmptyUrl("", "");
		
		ConnectConfig.getInstance().connectEntry(emptyUrl);
		System.out.println();
	}
	
	@Test
	public void test_service_provider() throws IOException{
		URL emptyUrl = UrlUtils.getEmptyUrl("", "");
		ConnectConfig.getInstance().connectEntry(emptyUrl);
		System.in.read();
		
	}
	
	public void test_service_consumer(){
		
	}

}
