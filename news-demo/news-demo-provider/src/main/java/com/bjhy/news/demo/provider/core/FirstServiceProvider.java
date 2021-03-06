package com.bjhy.news.demo.provider.core;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.bjhy.news.common.annotation.NewsListenerService;
import com.bjhy.news.common.util.NewsRpcUtil;
import com.bjhy.news.demo.api.FirstService;
import com.bjhy.news.demo.domain.User;

@Service
@NewsListenerService(FirstService.class)
public class FirstServiceProvider implements FirstService{

	@Override
	public void hello0(String str) {
		System.out.println("first hello0 "+str);
	}

	@Override
	public String hello1(String str) throws Exception{
//		System.out.println("测试异常情况下是否重试"+str);
		
		String s = "first hello1 "+str;
		System.out.println(s);
		return s;
	}
	
	@Override
	public String hello2(String str1, String str2, String str3) throws Exception {
		String str = "hello 2 : "+str1+","+str2+","+str3+NewsRpcUtil.getPid();
		System.out.println("write: "+str);
//		int i = 1/0;
		return "return : "+str;
	}

	@Override
	public User getUser(String username, Integer age, Date csrq, User user) {
		
		User user1 = new User();
		user1.setUsername(username+"_"+user.getUsername());
		user1.setAge(age+user.getAge());
		user1.setCsrq(csrq);
		System.out.println(user);
		return user1;
	}

	@Override
	public byte[] getStringByte(String dataPrefix) {
		System.out.println(dataPrefix);
		StringBuilder str = new StringBuilder(dataPrefix);
		for (int i = 0; i < 2000; i++) {
			str.append("类是否节点是否缴费基数了附件都是开发的设计费肯定是减肥肯定是删繁就简可视对讲了的设计费肯定是减肥肯定是发开发时间看电视剧弗兰克斯\r\n");
		}
		return str.toString().getBytes();
	}
}
