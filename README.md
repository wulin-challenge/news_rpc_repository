# news_rpc_repository
这是一个基于被动发现的同步rpc和异步消息通信框架,能够很好高效的实现两个无关系统之间同步/异步通信
## spring的详细使用方式请参考demo

## spring方式的使用:
### yml的配置

```
news: 
  zookeeper-address: 192.168.0.2:2181
  rocketmq-address: 192.168.0.80:9876
  rocketmq-publish-environment: dev
  rocketmq-publish-version: 1.1
  client-port: 5588
  client-topic: 5109
  client-tag: xxappId
```
### 配置说明
```
zookeeper-address:服务提供者与服务消费者共用的zookeeper地址
rocketmq-address : 服务提供者与服务消费者共用的rocketmq地址
rocketmq-publish-environment: rocketmq的发布环境
rocketmq-publish-version: rocketmq的发布版本
client-port : 当前消息rpc的端口
client-topic : 主题(监狱编号)
client-tag : 标签(应用的appId)

说明:
rocketmq-publish-environment: product|dev|test(rocketmq的发布环境,默认为生产环境)
rocketmq-publish-version: 1.1(rocketmq的发布版本,生产环境下建议不用添加版本,默认为空)  
```

### spring方式的集成
#### 引入maven依赖

```
<dependency>
  <groupId>com.bjhy</groupId>
  <artifactId>news-client-spring</artifactId>
  <version>1.0.1-SNAPSHOT</version>
</dependency>
```

#### 定义一个提供者与消费者 共同的 调用接口
```
package com.bjhy.news.demo.api;

import java.util.Date;

import com.bjhy.news.demo.domain.User;

public interface FirstService {
	
	void hello0(String str);
	
	String hello1(String str) throws Exception;
	
	User getUser(String username,Integer age ,Date csrq,User user);
}
```
#### 提供者提供服务
```
package com.bjhy.news.demo.provider.core;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.bjhy.news.common.annotation.NewsListenerService;
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
//		int i = 1/0;
		String s = "first hello1 "+str;
		System.out.println(s);
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
```

#### 消费者消费服务
##### 同步方式调用
```
	@Test
	public void test_invoke_service() throws InterruptedException{
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			String hello1 = null;
			try {
				hello1 = NewsUtil.syncSend(new TopicTag("5109", "xxappId"), FirstService.class).hello1(" hello world !");
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(i+"---"+hello1);
		}
		long end = System.currentTimeMillis();
		System.out.println((end-start));
		System.out.println((end-start)/1000);
		
	}
```
##### 异步方式调用
```
@Test
	public void test_invoke_async_service() throws InterruptedException{
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			
			try {
				AsyncSendResult asyncInvoke = NewsUtil.asyncSend(new TopicTag("5109", "xxappId"), FirstService.class).AsyncInvoke("hello1"," hello async ");
				System.out.println(i+"---"+asyncInvoke.getSendStatus());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		long end = System.currentTimeMillis();
		System.out.println((end-start));
		System.out.println((end-start)/1000);
	}
	
```



