package com.bjhy.news.common.domain;

/**
 * 提供者消费类型
 * @author wulin
 *
 */
public enum ProviderConsumerType {
	
	PROVIDER("provider","提供者"),
	CONSUMER("consumer","消费者"),
	BOTH("both","既是提供者又是消费者");
	
	private String code;
	private String name;
	
	private ProviderConsumerType(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	/**
	 * 通过code得到  提供者消费类型
	 * @param code
	 * @return
	 */
	public static ProviderConsumerType getProviderConsumerTypeByCode(String code){
		ProviderConsumerType[] values = values();
		for (ProviderConsumerType providerConsumerType : values) {
			if(providerConsumerType.getCode().equals(code)){
				return providerConsumerType;
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
