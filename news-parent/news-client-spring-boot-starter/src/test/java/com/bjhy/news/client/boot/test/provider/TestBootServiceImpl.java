package com.bjhy.news.client.boot.test.provider;

import org.springframework.stereotype.Service;

import com.bjhy.news.common.annotation.NewsListenerService;

@Service
@NewsListenerService(value=TestBootService.class,syncVersion="${version}")
public class TestBootServiceImpl implements TestBootService{

	@Override
	public String getName(String id) {
		return id+" : test名称";
	}

}
