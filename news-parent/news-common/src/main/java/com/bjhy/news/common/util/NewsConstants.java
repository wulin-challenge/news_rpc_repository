package com.bjhy.news.common.util;

import cn.wulin.ioc.URL;

/**
 * 消息rpc的功用常量类
 * @author wubo
 *
 */
public class NewsConstants {
	
	/**
	 * rocketmq发布环境(生产环境)
	 */
	public static final String ROCKETMQ_PUBLISH_ENVIRONMENT_PRODUCT = "product";
	
	/**
	 * rocketmq发布环境(开发环境)
	 */
	public static final String ROCKETMQ_PUBLISH_ENVIRONMENT_DEV = "dev";
	
	/**
	 * rocketmq发布环境(测试环境)
	 */
	public static final String ROCKETMQ_PUBLISH_ENVIRONMENT_TEST = "test";
	
	/**
	 * zk的根节点
	 */
	public static final String ZK_ROOT_NODE = "/news";
	
	/**
	 * 注册zk的临时序列节点前缀
	 */
	public static final String ZK_EPHEMERAL_NODE_PREFIX = "service_address_";
	
	/**
	 * rpc同步调用的版本key
	 */
	public static final String SYNC_VERSION_KEY = "syncVersion";
	
	/**
	 * rpc同步调用的超时时间key
	 */
	public static final String SYNC_TIMEOUT_KEY = "syncTimeout";
	
	/**
	 * rpc同步调用的注册协议key
	 */
	public static final String REGISTER_PROTOCOL_KEY = "register";
	
	/**
	 * 消息rpc中的rocketmq的默认组
	 */
	public static final String NEWS_DEFAULT_ROCKETMQ_GROUP = "news_default_rocketmq_group";
	

}
