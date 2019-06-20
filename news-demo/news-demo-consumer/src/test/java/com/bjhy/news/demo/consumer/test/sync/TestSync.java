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
public class TestSync {
	ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(80);
	
	
	@Test
	public void test_invoke_service() throws InterruptedException{
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			String hello1 = null;
			try {
				hello1 = NewsUtil.syncSend(new TopicTag("5109", "xxappId"), FirstService.class).hello1(" hello world !");
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(i+"---"+hello1);
		}
		long end = System.currentTimeMillis();
		System.out.println((end-start));
		System.out.println((end-start)/1000);
		
	}
	
	@Test
	public void test_invoke_service2() throws InterruptedException{
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			
			buildParams2(i);
		}
		long end = System.currentTimeMillis();
		System.out.println((end-start));
		System.out.println((end-start)/1000);
	}

	@Test
	public void test_invoke_service_mutil_Thread() throws InterruptedException{
		List<Callable<Void>> tasks = new ArrayList<Callable<Void>>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			final int j = i;
			
			Callable<Void> callable =new Callable<Void>(){

				@Override
				public Void call() throws Exception {
					buildParams2(j);
					return null;
				}
			};
			tasks.add(callable);
		}
		newFixedThreadPool.invokeAll(tasks);
		long end = System.currentTimeMillis();
		System.out.println((end-start));
		System.out.println((end-start)/1000);
	}
	
	private void buildParams2(int i) {
		try {
			String username = i+"__李四";
			Integer age = i;
			Date csrq = new Date();
			
			User user = new User();
			user.setUsername(username+"__"+i);
			user.setAge(i+1);
			user.setCsrq(csrq);
			
			User user2 = NewsUtil.syncSend(new TopicTag("5109", "xxappId"), FirstService.class).getUser(username, age, csrq, user);
			
			System.out.println(i+"---"+user2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试默认主题和tag
	 * @throws InterruptedException
	 */
	@Test
	public void test_defualt_topicTag() throws InterruptedException{
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			String hello1 = null;
			try {
				hello1 = NewsUtil.syncSend(FirstService.class).hello1(" hello! 默认的topic和tag .");
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
