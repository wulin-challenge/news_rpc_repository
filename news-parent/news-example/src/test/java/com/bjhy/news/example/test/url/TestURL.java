package com.bjhy.news.example.test.url;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.wulin.ioc.URL;

/**
 * 测试url
 * @author ThinkPad
 *
 */
public class TestURL {
	
	@Test
	public void testBuildURL(){
		Map<String,String> params = new HashMap<String,String>();
		
		URL url = new URL("zookeeperRegistry", "0.0.0.0", 123);
		
		String serviceString = url.toServiceString();
		System.out.println(params);
		System.out.println(serviceString);
//		UrlUtils.
		
	}

}
