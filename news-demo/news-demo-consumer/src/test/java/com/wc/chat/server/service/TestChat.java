package com.wc.chat.server.service;

import java.io.IOException;
import java.util.Scanner;

import org.apel.gaia.app.boot.AppStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bjhy.news.common.util.NewsUtil;

@SpringBootTest(classes=AppStarter.class)
@RunWith(SpringRunner.class)
public class TestChat {
	
	@Test
	public void sendChat() throws IOException {
		for (int i = 0; i < 100000; i++) {
			
			
			Scanner sc = new Scanner(System.in);
			
			String msg = NewsUtil.syncSend(UserService.class).send("wuchao", sc.nextLine());
//			boolean send = NewsUtil.syncSend(UserService.class).send("wuchao", "你好!");
			System.out.println(msg+"\n");
		}
	}

}
