package com.bjhy.news.demo.api;

import java.util.Date;

import com.bjhy.news.demo.domain.User;

public interface FirstService {
	
	void hello0(String str);
	
	String hello1(String str) throws Exception;
	
	User getUser(String username,Integer age ,Date csrq,User user);
}
