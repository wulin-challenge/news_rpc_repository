package com.bjhy.news.demo.api;

import java.util.Date;

import com.bjhy.news.demo.domain.User;

public interface FirstService {
	
	void hello0(String str);
	
	String hello1(String str) throws Exception;
	
    String hello2(String str1,String str2,String str3) throws Exception;
	
	User getUser(String username,Integer age ,Date csrq,User user);
}
