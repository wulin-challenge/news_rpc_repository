package com.bjhy.news.common.connect;

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
	String zookeeperId();
	
	/**
	 * zookeeper端口	
	 * @return
	 */
	Integer zookeeperPort();
	
	/**
	 * rocketmqIp
	 * @return
	 */
	String rocketmqIp();
	
	/**
	 * rocketmq端口
	 * @return
	 */
	Integer rocketmqPort();
	
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
	 * rocketmq的发布环境
	 * 注意:该参数仅在发送异步消息是有效
	 */
	String rocketmqPublishEnvironment();
	
	/**
	 * rocketemq的发布版本,主要是用于开发环境中个人测试
	 * 注意:该参数仅在发送异步消息是有效
	 */
	String rocketmqPublishVersion();
	
}
