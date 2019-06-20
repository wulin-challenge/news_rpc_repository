package com.bjhy.news.common.util;

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
	 * 注册zk的临时序列节点key
	 */
	public static final String ZK_EPHEMERAL_NODE_KEY = "service_address_node_key";
	
	/**
	 * 当前服务启动的进程pid
	 */
	public static final String PID = "pid";
	
	/**
	 * rpc同步调用的版本key
	 */
	public static final String SYNC_VERSION_KEY = "syncVersion";
	
	/**
	 * rpc同步调用的超时时间key
	 */
	public static final String SYNC_TIMEOUT_KEY = "syncTimeout";
	
	/**
	 * 客户端主题key
	 */
	public static final String CLIENT_TOPIC_KEY = "clientTopic";
	
	/**
	 * 客户端标签key
	 */
	public static final String CLIENT_TAG_KEY = "clientTag";
	
	/**
	 * 服务接口key
	 */
	public static final String SERVICE_INTERFACE_KEY = "serviceInterface";
	
	/**
	 * rpc同步调用的注册协议key
	 */
	public static final String REGISTER_PROTOCOL_KEY = "news_rpc_register";
	
	/**
	 * 重试次数key
	 */
	public static final String RETRIES_KEY = "retries";
	
	/**
	 * 调用策略集群key
	 */
	public static final String CLUSTER_KEY = "cluster";
	
	/**
	 * 负载均衡key
	 */
	public static final String LOADBALANCE_KEY = "loadbalance";
	
	/**
	 * 消息rpc中的rocketmq的默认消费组前缀
	 */
	public static final String NEWS_ROCKETMQ_GROUP_CONSUMER = "news_default_rocketmq_group_consumer";
	/**
	 * 消息rpc中的rocketmq的默认生产组前缀
	 */
	public static final String NEWS_ROCKETMQ_GROUP_PRODUCER = "news_default_rocketmq_group_producer";
	
	/**
	 * 类别Key
	 */
	public static final String CATEGORY_KEY = "category";
	/**
	 * zk新增事件
	 */
	public static final String ZOOKEEPER_NODE_ADDED_EVENT = "node_added";
	
	/**
	 * zk更新事件
	 */
	public static final String ZOOKEEPER_NODE_UPDATED_EVENT = "node_updated";
	
	/**
	 * zk删除事件
	 */
	public static final String ZOOKEEPER_NODE_REMOVED_EVENT = "node_removed";
	
	/**
	 * zk重连事件
	 */
	public static final String ZOOKEEPER_RECONNECTED_EVENT = "reconnected";
	
	/**
	 * 默认同步超时时间5秒
	 */
	public static final int DEFUALT_SYNC_TIMEOUT = 5000;
	
	/**
	 * 默认主题
	 */
	public static final String DEFAULT_TOPIC = "news_topic";
	
	/**
	 * 默认tag标签
	 */
	public static final String DEFAULT_TAG = "news_tag";
}
