package com.bjhy.news.demo.dubbo.provide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.bjhy.news.client.spring.init.InitializingNewsRpcSpring;
import com.bjhy.news.demo.api.FirstService;

@Component
public class InitDubbo implements InitializingNewsRpcSpring{
	
	@Autowired
	private FirstService firstService;

	@Override
	public void init(ApplicationContext applicationContext) throws Exception {
		String hello1 = firstService.hello1("test");
		System.out.println(hello1);
		System.out.println(hello1);
	}

}
