package com.bjhy.news.client.boot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages="com.bjhy.news")
public class NewsBootStarter {
	public static void main(String[] args) {
	    SpringApplication.run(NewsBootStarter.class, args);
		
	    System.out.println("------------启动成功!");
	}
}
