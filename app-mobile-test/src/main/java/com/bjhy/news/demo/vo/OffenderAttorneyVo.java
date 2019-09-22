package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 罪犯律师VO。和com.bjhy.titan.manager.core.domain.MeetsLawyerEntityDetail中的属性一致。
 * 
 * @author wlh
 *
 */
public class OffenderAttorneyVo implements Serializable {

	private static final long serialVersionUID = -2497685752435233543L;
	private String id;
	// MeetsLawyerEntity表主键ID
	private String parentId;
	// 代理人姓名
	private String agentName;
	// 性别
	private String sex;
	// 身份证号
	private String idNumber;
	// 与被代理人关系
	private String relation;
	// 执业（工作）证号
	private String workCardNumber;
	// 工作单位
	private String workUnit;
	// 介绍信号
	private String introductNumber;
	// 与罪犯是否建立委托关系(是、否)
	private String establishRelation;
	// 会见次数（系统增设记忆功能，自动记忆累加该律师会见次数）
	private String meetingCount;
	// 创建时间
	private Date createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getWorkCardNumber() {
		return workCardNumber;
	}

	public void setWorkCardNumber(String workCardNumber) {
		this.workCardNumber = workCardNumber;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getIntroductNumber() {
		return introductNumber;
	}

	public void setIntroductNumber(String introductNumber) {
		this.introductNumber = introductNumber;
	}

	public String getEstablishRelation() {
		return establishRelation;
	}

	public void setEstablishRelation(String establishRelation) {
		this.establishRelation = establishRelation;
	}

	public String getMeetingCount() {
		return meetingCount;
	}

	public void setMeetingCount(String meetingCount) {
		this.meetingCount = meetingCount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
