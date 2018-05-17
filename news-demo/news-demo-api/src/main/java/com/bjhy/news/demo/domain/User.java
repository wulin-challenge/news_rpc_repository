package com.bjhy.news.demo.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private String username;
	
	private Integer age;
	
	private Date csrq;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getCsrq() {
		return csrq;
	}

	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE");
		String format = sdf.format(csrq);
		return "{username:"+username+",age:"+age+",csrq:"+format+"}";
	}
	
	
}
