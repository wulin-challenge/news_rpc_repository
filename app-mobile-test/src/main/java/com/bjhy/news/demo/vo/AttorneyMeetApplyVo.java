package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 律师会见VO。对应com.bjhy.titan.manager.core.domain.MeetsLawyerEntity
 * 
 * @author wlh
 *
 */
public class AttorneyMeetApplyVo implements Serializable {

	private static final long serialVersionUID = -4449726097994743552L;
	// id
	private String id;
	// 罪犯律师
	private List<OffenderAttorneyVo> details = new ArrayList<>();
	// 代理人姓名
	private String agentName;
	//会见人数（提供给app端进行显示）
	private Integer hjrsl;
	// 性别
	private String sex;
	// 份证号
	private String idNumber;
	// 与被代理人关系
	private String relation;
	// 执业（工作）证号
	private String workCardNumber;
	// 工作单位
	private String workUnit;
	// 介绍信号
	private String introductNumber;
	// 会见时间
	private Date meetingTime;
	// 会见事由
	private String meetingCause;
	// 与罪犯是否建立委托关系(是、否)
	private String establishRelation;
	// 会见次数（系统增设记忆功能，自动记忆累加该律师会见次数）
	private String meetingCount;
	// 罪犯信息
	private CriminalBaseInfoVo baseInfo;
	// 处理民警
	private String police;
	// 创建时间
	private Date createDate;
	// 签收人
	private String assignee;
	private String processId;
	private String processState;
	private Boolean isBack;
	private Boolean isDelete;
	private String currentDelegate;
	
	private Map checkRecords;

	public Map getCheckRecords() {
		return checkRecords;
	}
	
	public Integer getHjrsl() {
		return hjrsl;
	}

	public void setHjrsl(Integer hjrsl) {
		this.hjrsl = hjrsl;
	}

	public void setCheckRecords(Map checkRecords) {
		this.checkRecords = checkRecords;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<OffenderAttorneyVo> getDetails() {
		return details;
	}

	public void setDetails(List<OffenderAttorneyVo> details) {
		this.details = details;
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

	public Date getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(Date meetingTime) {
		this.meetingTime = meetingTime;
	}

	public String getMeetingCause() {
		return meetingCause;
	}

	public void setMeetingCause(String meetingCause) {
		this.meetingCause = meetingCause;
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

	public CriminalBaseInfoVo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(CriminalBaseInfoVo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public String getPolice() {
		return police;
	}

	public void setPolice(String police) {
		this.police = police;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessState() {
		return processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}

	public Boolean getIsBack() {
		return isBack;
	}

	public void setIsBack(Boolean isBack) {
		this.isBack = isBack;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getCurrentDelegate() {
		return currentDelegate;
	}

	public void setCurrentDelegate(String currentDelegate) {
		this.currentDelegate = currentDelegate;
	}

}
