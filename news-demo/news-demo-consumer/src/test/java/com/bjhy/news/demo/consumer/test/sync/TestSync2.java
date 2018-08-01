package com.bjhy.news.demo.consumer.test.sync;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apel.gaia.app.boot.AppStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bjhy.news.common.domain.TopicTag;
import com.bjhy.news.common.util.NewsUtil;
import com.bjhy.news.demo.api.FirstService;
import com.bjhy.news.demo.domain.User;

@SpringBootTest(classes=AppStarter.class)
@RunWith(SpringRunner.class)
public class TestSync2 {
	ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(80);
	
	
	@Test
	public void test_invoke_service() throws InterruptedException{
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			String hello1 = null;
			try {
				hello1 = NewsUtil.syncSend(new TopicTag("5109", "xxappId"), FirstService.class)
						.hello2("aa", null, "cc");
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(i+"---"+hello1);
		}
		long end = System.currentTimeMillis();
		System.out.println((end-start));
		System.out.println((end-start)/1000);
	}
	
	

}
