package com.bjhy.news.demo.dubbo.provide;

import java.util.Date;
import java.util.List;

import org.apel.poseidon.api.CasService;
import org.apel.poseidon.api.domain.AppClientInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjhy.news.common.annotation.NewsListenerService;
import com.bjhy.news.demo.api.FirstService;
import com.bjhy.news.demo.domain.User;

@Service
@NewsListenerService(FirstService.class)
public class FirstServiceDubboProvider implements FirstService{
	
	@Reference
	private CasService casService;

	@Override
	public void hello0(String str) {
		System.out.println("first hello0 "+str);
	}

	@Override
	public String hello1(String str) throws Exception{
//		int i = 1/0;
		String s = "first hello1 "+str;
		System.out.println(s);
		List<AppClientInfo> findAllAppClients = casService.findAllAppClients();
		return s;
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
}