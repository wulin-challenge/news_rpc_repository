package com.bjhy.news.demo.consumer.test.sync;

import org.apel.gaia.app.boot.AppStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bjhy.news.common.domain.AsyncSendResult;
import com.bjhy.news.common.domain.TopicTag;
import com.bjhy.news.common.util.NewsUtil;
import com.bjhy.news.demo.api.FirstService;

@SpringBootTest(classes=AppStarter.class)
@RunWith(SpringRunner.class)
public class TestAsync2 {
	
	@Test
	public void test_invoke_async_service2() throws InterruptedException{
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			
			try {
				AsyncSendResult asyncInvoke = NewsUtil.asyncSend(new TopicTag("5109", "xxappId"), FirstService.class).asyncInvoke("hello1"," hello async 测试拉消息-- "+i);
				System.out.println(i+"---"+asyncInvoke.getSendStatus());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		long end = System.currentTimeMillis();
		System.out.println((end-start));
		System.out.println((end-start)/1000);
	}
}
