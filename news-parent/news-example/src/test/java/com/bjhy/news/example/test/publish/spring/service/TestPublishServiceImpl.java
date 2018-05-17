package com.bjhy.news.example.test.publish.spring.service;

import org.springframework.stereotype.Service;

import com.bjhy.news.common.annotation.NewsListenerService;

@Service
@NewsListenerService(TestPublishService.class)
public class TestPublishServiceImpl implements TestPublishService{

	@Override
	public void hello(String str) {
		
		System.out.println("first hello : "+str);
		
	}

	@Override
	public String hello2(String str) {
		String r = "second hello2 : "+str;
		System.out.println("second hello2 : "+str);
		return r;
	}

}
