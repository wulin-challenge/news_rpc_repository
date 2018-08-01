package com.bjhy.news.demo.consumer.test.sync;

import java.io.IOException;
import java.util.Date;

import org.apel.gaia.app.boot.AppStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bjhy.news.common.domain.AsyncSendResult;
import com.bjhy.news.common.domain.TopicTag;
import com.bjhy.news.common.util.NewsUtil;
import com.bjhy.news.demo.api.FirstService;
import com.bjhy.news.demo.domain.User;

@SpringBootTest(classes=AppStarter.class)
@RunWith(SpringRunner.class)
public class TestAsync {
	
	@Test
	public void test_invoke_async_service() throws InterruptedException{
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			
			try {
				AsyncSendResult asyncInvoke = NewsUtil.asyncSend(new TopicTag("5109", "xxappId"), FirstService.class).asyncInvoke("hello1"," hello async ");
				System.out.println(i+"---"+asyncInvoke.getSendStatus());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		long end = System.currentTimeMillis();
		System.out.println((end-start));
		System.out.println((end-start)/1000);
	}
	
	@Test
	public void test_invoke_async_service2() throws InterruptedException{
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			
			try {
				String username = i+"__张三";
				Integer age = i;
				Date csrq = new Date();
				
				User user = new User();
				user.setUsername(username+"__"+i);
				user.setAge(i+1);
				user.setCsrq(csrq);
				
				AsyncSendResult asyncInvoke = NewsUtil.asyncSend(new TopicTag("5109", "xxappId"), FirstService.class)
						.asyncInvoke("getUser",username,age,csrq,user);
				System.out.println(i+"---"+asyncInvoke.getSendStatus());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		long end = System.currentTimeMillis();
		System.out.println((end-start));
		System.out.println((end-start)/1000);
	}
	
	@Test
	public void test_invoke_sync_dubbo_service() throws IOException, Exception{
		
//		String hello1 = NewsUtil.syncSend(new TopicTag("5109", "xxappId"), FirstService.class).hello1("xxxxx");
		
		AsyncSendResult asyncInvoke = NewsUtil.asyncSend(new TopicTag("5109", "xxappId"), FirstService.class).asyncInvoke("hello1", "xxxxx");
		System.out.println(asyncInvoke.getSendStatus());
		System.out.println();
	}
	

}
