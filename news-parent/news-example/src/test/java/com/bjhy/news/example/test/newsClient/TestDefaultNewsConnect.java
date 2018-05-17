package com.bjhy.news.example.test.newsClient;

import org.junit.Test;

import com.bjhy.news.common.connect.NewsConnect;

import cn.wulin.ioc.extension.InterfaceExtensionLoader;

/**
 * 测试使用 InterfaceExtensionLoader
 * @author wubo
 *
 */
public class TestDefaultNewsConnect {
	
	/**
	 * 测试使用 InterfaceExtensionLoader 自动注入  NewsConnect 的默认实现
	 */
	@Test
	public void testInterfaceExtensionLoader_NewsConnect(){
		NewsConnect newsConnect = InterfaceExtensionLoader.getExtensionLoader(NewsConnect.class).getAdaptiveExtension();
		String zookeeperId = newsConnect.zookeeperId();
		Integer zookeeperPort = newsConnect.zookeeperPort();
		System.out.println(zookeeperId);
		System.out.println(zookeeperPort);
	}

}
