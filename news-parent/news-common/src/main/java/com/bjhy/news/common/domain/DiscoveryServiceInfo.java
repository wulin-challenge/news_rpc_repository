package com.bjhy.news.common.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现服务信息
 * @author wubo
 *
 */
public class DiscoveryServiceInfo {
	
	/**
	 * 远程客户端的主题(topic)
	 */
	private String clientTopic;
	
	/**
	 * 远程客户端的标签(tag)
	 */
	private String clientTag;
	
	/**
	 * 发布服务的Service的class
	 */
	private Class<?> serviceClass;
	
	/**
	 * 服务的具体详细
	 */
	private List<DiscoveryServiceDetailInfo> discoveryServiceDetailInfoList = new ArrayList<>();

	public String getClientTopic() {
		return clientTopic;
	}

	public void setClientTopic(String clientTopic) {
		this.clientTopic = clientTopic;
	}

	public String getClientTag() {
		return clientTag;
	}

	public void setClientTag(String clientTag) {
		this.clientTag = clientTag;
	}

	public Class<?> getServiceClass() {
		return serviceClass;
	}

	public void setServiceClass(Class<?> serviceClass) {
		this.serviceClass = serviceClass;
	}

	public List<DiscoveryServiceDetailInfo> getDiscoveryServiceDetailInfoList() {
		return discoveryServiceDetailInfoList;
	}

	public void setDiscoveryServiceDetailInfoList(List<DiscoveryServiceDetailInfo> discoveryServiceDetailInfoList) {
		this.discoveryServiceDetailInfoList = discoveryServiceDetailInfoList;
	}
}
