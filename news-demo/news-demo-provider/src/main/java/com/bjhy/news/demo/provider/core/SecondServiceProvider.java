package com.bjhy.news.demo.provider.core;

import org.springframework.stereotype.Service;

import com.bjhy.news.common.annotation.NewsListenerService;
import com.bjhy.news.demo.api.SecondService;

@Service
@NewsListenerService(SecondService.class)
public class SecondServiceProvider implements SecondService{

	@Override
	public void invokeService0(String str) {
		System.out.println("invoke 0 "+str);
		
	}

	@Override
	public String invokeService1(String str) {
		String s = "invoke 0 "+str;
		System.out.println(s);
		return s;
	}

}
