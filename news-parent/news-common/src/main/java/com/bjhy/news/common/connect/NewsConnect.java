package com.bjhy.news.common.connect;

import com.bjhy.news.common.domain.RocketmqNewsType;

import cn.wulin.ioc.extension.SPI;

/**
 * 消息rpc的连接接口
 * @author wubo
 */
@SPI("default.client.connect")
public interface NewsConnect {
	
	/**
	 * zookeeperIp
	 * @return 
	 */
	String zookeeperIp();
	
	/**
	 * zookeeper端口	
	 * @return
	 */
	Integer zookeeperPort();
	
	/**
	 * rocketmq地址,多个地址之间采用 ;隔开
	 * @return
	 */
	String rocketmqAddress();
	
	/**
	 * 客户端Id
	 * @return
	 */
	String clientId();
	
	/**
	 * 客户端名称
	 * @return
	 */
	String clientName();
	
	/**
	 * 客户端Ip
	 * @return
	 */
	String clientIp();
	
	/**
	 * 客户端端口
	 * @return
	 */
	Integer clientPort();
	
	/**
	 * 客户端telnet端口,默认为 clientPort+1
	 * @return
	 */
	Integer clientTelnetPort();
	
	/**
	 * 客户端主题
	 * @return
	 */
	String clientTopic();
	
	/**
	 * 客户端标签
	 * @return
	 */
	String clientTag();
	
	/**
	 * 重试次数,当不想要重试,请使用赋值为0
	 * 当cluster使用 failover策略才生效
	 * @return
	 */
	Integer retries();
	
	/**
	 * 集群策略
	 * @return
	 */
	String cluster();
	
	/**
	 * 负载均衡策略
	 * @return
	 */
	String loadbalance();
	
	/**
	 * rocketmq的发布环境
	 * 注意:该参数仅在发送异步消息是有效
	 */
	String rocketmqPublishEnvironment();
	
	/**
	 * rocketemq的发布版本,主要是用于开发环境中个人测试
	 * 注意:该参数仅在发送异步消息是有效
	 */
	String rocketmqPublishVersion();
	
	/**
	 * rocketmq的消息类型,general:普通消息,order:顺序消息,transactional:事务消息
	 * @return
	 */
	RocketmqNewsType rocketmqNewsType();
	
	/**
	 * rocketmq的生产组和消费组是否唯一
	 * 强调:生产组与消费组之间的名称一定不能一样
	 * 说明:默认情况下,若应用程序配置的topic和appid以及版本号以及rocketmq的发布环境一样的情况下,两个相同的应用程序启动后会各自分担一部分broker的队列
	 * @return
	 */
	boolean rocketmqIsUniqueGroup();
	
	/**
	 * rocketmq顺序消息队列
	 * @return
	 */
	Integer rocketmqOrderQueue();
	
	/**
	 * 传输数据的优先负载,65536->64kb大小的数据
	 * @return
	 */
	Integer payload();
	
	/**
	 * 用于划定 提供者和消费者 接口调用分组
	 * <p> 例如: provider1(group=1),provider2(group=2),provider3(group=2).
	 * <p> consumer1(group=1),consumer2(group=2)
	 * <p> 那么consumer1将只调用group=1的provider提供的接口
	 * <p> 那么consumer2将只调用group=2的provider提供的接口
	 * <p> 配置文件中不指定的情况provider和consumer都属于news_interface_group组
	 * 
	 * @return
	 */
	String interfaceGroup();
	
	/**
	 * 指示当前服务是提供者还是消费者,还是既是提供者又是消费
	 * <p> 提供者: provider
	 * <p> 消费者: consumer
	 * <p> 既是提供者又是消费者: both
	 * <p> 配置文件中不指定的情况下,该值通过计算所得
	 * @return
	 */
	String providerConsumer();
	
	/**
	 * 设置{@link #providerConsumer()}的值
	 */
	void setProviderConsumer(String providerConsumer);
	
}
