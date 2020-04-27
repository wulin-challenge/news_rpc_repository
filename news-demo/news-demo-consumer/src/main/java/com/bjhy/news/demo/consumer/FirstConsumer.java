package com.bjhy.news.demo.consumer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.bjhy.news.common.domain.TopicTag;
import com.bjhy.news.common.util.NewsUtil;
import com.bjhy.news.demo.api.FirstService;

@Component
public class FirstConsumer implements InitializingBean{
	private static final List<String> topics = Arrays.asList("5101","5102","5103");
	

	@Override
	public void afterPropertiesSet() throws Exception {
//		for (int i = 0; i < 10000; i++) {
//			for (String topic : topics) {
//				String hello1 = null;
//				try {
//					hello1 = NewsUtil.syncSend(new TopicTag(topic, "xxappId"), FirstService.class).hello1(" hello world !");
//				} catch (Exception e) {
//					System.err.println(topic+": 错误信息: "+e.getMessage());
//				}
//				System.out.println(i+"---"+hello1);
//			}
//			System.in.read();
//		}
//		System.out.println("10000 次运行结束");
	}

}
