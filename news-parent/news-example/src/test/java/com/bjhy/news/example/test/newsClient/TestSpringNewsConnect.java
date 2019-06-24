package com.bjhy.news.example.test.newsClient;

import org.apel.gaia.app.boot.AppStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bjhy.news.common.connect.NewsConnect;

import cn.wulin.ioc.extension.InterfaceExtensionLoader;

@SpringBootTest(classes=AppStarter.class)
@RunWith(SpringRunner.class)
public class TestSpringNewsConnect {
	
	@Test
	public void testSpringLoadNewsConnect(){
		NewsConnect newsConnect = InterfaceExtensionLoader.getExtensionLoader(NewsConnect.class).getAdaptiveExtension();
		
		String zookeeperId = newsConnect.zookeeperIp();
		Integer zookeeperPort = newsConnect.zookeeperPort();
		System.out.println(zookeeperId);
		System.out.println(zookeeperPort);
	}

}
