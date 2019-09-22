package com.bjhy.news.demo.consumer.test.sync;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apel.gaia.app.boot.AppStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bjhy.news.common.domain.TopicTag;
import com.bjhy.news.common.util.NewsConstants;
import com.bjhy.news.common.util.NewsUtil;
import com.bjhy.news.demo.api.FirstService;

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
				hello1 = NewsUtil.syncSend(new TopicTag("5109", "xxappId"), FirstService.class).hello2("aa", null, "cc");
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(i+"---"+hello1);
		}
		long end = System.currentTimeMillis();
		System.out.println((end-start));
		System.out.println((end-start)/1000);
	}
	
	/**
	 * 测试异常情况下 rpc的错误处理
	 */
	@Test
	public void test_invoke_dubbo_provider_service(){
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			String hello1 = null;
			try {
				hello1 = NewsUtil.syncSend(new TopicTag("5199", "xxappIdx",5000), FirstService.class).hello2("aa", null, "cc");
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(i+"---"+hello1);
		}
		long end = System.currentTimeMillis();
		System.out.println((end-start));
		System.out.println((end-start)/1000);
	}
	
	

	/**
	 * 测试字符串Byte
	 * @throws IOException 
	 */
	@Test
	public void testStringByte() {
		
		for (int i = 0; i < 100; i++) {
			
			try {
				
				TopicTag tt = new TopicTag(NewsConstants.DEFAULT_TOPIC, NewsConstants.DEFAULT_TAG,10000);
				tt.setCluster("failfast");
				byte[] data = NewsUtil.syncSend(tt, FirstService.class).getStringByte("String : ");
				
				String str = new String(data);
				System.out.println(str);

				System.out.println(data.length);
				
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	/**
	 * 测试字符串Byte
	 * @throws IOException 
	 */
	@Test
	public void testStringByteMultiThread() throws IOException {
		
		for (int i = 0; i < 100000; i++) {
			newFixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TopicTag tt = new TopicTag(NewsConstants.DEFAULT_TOPIC, NewsConstants.DEFAULT_TAG,10000);
						tt.setCluster("failfast");
						byte[] data = NewsUtil.syncSend(tt, FirstService.class).getStringByte("String : ");
						
						String str = new String(data);
						System.out.println(str);

						System.out.println(data.length);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		System.in.read();
	}

}
