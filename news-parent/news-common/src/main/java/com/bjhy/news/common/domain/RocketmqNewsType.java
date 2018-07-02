package com.bjhy.news.common.domain;

/**
 * rocketmq的消息类型
 * @author wubo
 */
public enum RocketmqNewsType {
	
	GENERAL("general","普通消息"),
	ORDER("order","顺序消息"),
	TRANSACTIONAL("transactional","事务消息");
	
	private String code;
	private String name;
	
	private RocketmqNewsType(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	/**
	 * 通过code得到  rocketmq的消息类型
	 * @param code
	 * @return
	 */
	public static RocketmqNewsType getRocketmqNewsTypeByCode(String code){
		RocketmqNewsType[] values = values();
		for (RocketmqNewsType rocketmqNewsType : values) {
			if(rocketmqNewsType.getCode().equals(code)){
				return rocketmqNewsType;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
