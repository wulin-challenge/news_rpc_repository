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

	@Override
	public String toString() {
		String clazz = serviceClass != null? serviceClass.getName():"";
		StringBuilder s = new StringBuilder("clientTopic:"+clientTopic+">clientTag:"+clientTag+">className:"+clazz);
		for (DiscoveryServiceDetailInfo detailInfo : discoveryServiceDetailInfoList) {
			s.append("(serviceIp:"+detailInfo.getServiceIp());
			s.append(">servicePort:"+detailInfo.getServicePort());
			s.append(">version:"+detailInfo.getVersion());
			s.append(">pid:"+detailInfo.getPid());
			s.append(">timeout:"+detailInfo.getTimeout()+")");
		}
		return s.toString();
	}
	
	
}
