package com.bjhy.news.common.domain;

/**
 * 发布服务信息
 * @author wubo
 */
public class PublishServiceInfo {
	
	/**
	 * 发布服务的Service的class
	 */
	private Class<?> serviceClass;
	
	/**
	 * 同步调用时的版本
	 */
	private String syncVersion;
	
	/**
	 * 同步调用时的超时时间
	 */
	private Integer syncTimeout;
	
	/**
	 * 发布服务的service的实现对象
	 */
	private Object serviceImplObject;

	public Class<?> getServiceClass() {
		return serviceClass;
	}

	public void setServiceClass(Class<?> serviceClass) {
		this.serviceClass = serviceClass;
	}

	public String getSyncVersion() {
		return syncVersion;
	}

	public void setSyncVersion(String syncVersion) {
		this.syncVersion = syncVersion;
	}

	public Integer getSyncTimeout() {
		return syncTimeout;
	}

	public void setSyncTimeout(Integer syncTimeout) {
		this.syncTimeout = syncTimeout;
	}

	public Object getServiceImplObject() {
		return serviceImplObject;
	}

	public void setServiceImplObject(Object serviceImplObject) {
		this.serviceImplObject = serviceImplObject;
	}
}
