package com.bjhy.news.demo.consumer;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apel.gaia.app.boot.AppStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bjhy.news.common.domain.TopicTag;
import com.bjhy.news.common.util.NewsUtil;
import com.bjhy.news.demo.api.WlryABService;
import com.bjhy.platform.commons.pager.PageBean;

@SpringBootTest(classes=AppStarter.class)
@RunWith(SpringRunner.class)
public class TestApp {
	
	ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(80);
	

	@Test
	public void invokeWlryABServiceSingle(){
		invokeWlryABServiceSingle0();
	}

	private void invokeWlryABServiceSingle0() {
		
		for (int i = 0; i < 10000; i++) {
			PageBean pageBean = new PageBean();
			
			 try {
				PageBean pageBean2 = NewsUtil.syncSend(new TopicTag("5121", "titan_test",5000), WlryABService.class).getInAndOutApplyNotBeenDoneList(pageBean, "5101241989042101941");
//				PageBean pageBean1 = NewsUtil.syncSend(new TopicTag("5124", "titan_test",5000), WlryABService.class).getInAndOutApplyNotBeenDoneList(pageBean, "511021196804102150");
				System.out.println(" : "+((Map)pageBean2.getItems().get(0)).get("zz"));
//				System.out.println(" : "+((Map)pageBean1.getItems().get(0)).get("zz"));
//				System.in.read();
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		news.client-topic=5121
//		 news.client-tag=titan_test
	}
	
	@Test
	public void invokeWlryABService(){
		invokeWlryABService0();
	}

	private void invokeWlryABService0() {
		
		for (int i = 0; i < 10000; i++) {
			final int j = i;
			newFixedThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					PageBean pageBean = new PageBean();
					
					 try {
//						PageBean pageBean2 = NewsUtil.syncSend(new TopicTag("5121", "titan_test",5000), WlryABService.class).getInAndOutApplyNotBeenDoneList(pageBean, "510124198904210194");
						PageBean pageBean2 = NewsUtil.syncSend(new TopicTag("5124", "titan_test",5000), WlryABService.class).getInAndOutApplyNotBeenDoneList(pageBean, "511021196804102150");
						System.out.println(j+" : "+((Map)pageBean2.getItems().get(0)).get("zz"));
//						System.in.read();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		news.client-topic=5121
//		 news.client-tag=titan_test
	}
}
