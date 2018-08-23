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
  rocketmq-news-type: general
  rocketmq-is-unique-group: false
  rocketmq-order-queue: 0
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
rocketmq-news-type: rocketmq的消息类型,general:普通消息,order:顺序消息,transactional:事务消息(默认 general)
rocketmq-is-unique-group: rocketmq的生产组和消费组是否唯一(1. 强调:生产组与消费组之间的名称一定不能一样. 2. 说明:默认情况下,若应用程序配置的 topic和appid以及版本号以及rocketmq的发布环境一样的情况下,两个相同的应用程序启动后会各自分担一部分broker的队列)
rocketmq-order-queue: rocketmq顺序消息队列

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
##### 提供者的yml配置
```
news: 
  zookeeper-address: 192.168.0.2:2181
  rocketmq-address: 192.168.0.80:9876
  rocketmq-publish-environment: dev
  rocketmq-publish-version: 1.1
  client-port: 5588
  client-topic: 5109
  client-tag: xxappId
  rocketmq-news-type: general
  rocketmq-is-unique-group: false
  rocketmq-order-queue: 0
```

##### 提供者的具体代码实现
说明: 提供者的实现主要是打上  
@Service  
@NewsListenerService(FirstService.class)  
者两个注解  

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
##### 消费者yml配置
```
news: 
  zookeeper-address: 192.168.0.2:2181
  rocketmq-address: 192.168.0.80:9876
  rocketmq-publish-environment: test
  rocketmq-publish-version: 1.1
  client-port: 5589
  client-topic: 5110
  client-tag: xxappId2
  rocketmq-news-type: general
  rocketmq-is-unique-group: false
  rocketmq-order-queue: 0
```
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
				AsyncSendResult asyncInvoke = NewsUtil.asyncSend(new TopicTag("5109", "xxappId"), FirstService.class).asyncInvoke("hello1"," hello async ");
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

### 关于消息rpc的 使用友情提示
```
若消费方 调用的接口只是获取数据,那就采用同步调用
若消费方 调用的接口是要修改提供服务方的数据,而又可以不需要及时返回值,那就强烈介意采用异步调用,因为可以避免分布式事务问题
若消费方 调用的接口既要修该提供服务方的数据,又要及时返还值,那请自己处理分布式事务问题
```

### 使用spring InitializingBean 注意事项
```
在应用项目中若使用了spring的InitializingBean ,且在afterPropertiesSet方法中条用了 news rpc的接口,那么请将InitializingBean接口换为InitializingNewsRpcSpring 接口
```




