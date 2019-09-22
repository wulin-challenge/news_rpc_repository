package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 亲情会见申请（业务实体）
 * @author 86183
 *
 */
public class KinshipMeetApplyVo implements Serializable {
	
	private static final long serialVersionUID = 7041578177489338707L;

	private String assignee;
	
	private String id;

	private Date createDate;
	//提供给app端进行显示，显示会见人数量
	private Integer hjrsl;
	// 会见时间
	private Date  hjsj;
	// 会见原因
	private String  hjyy;
	// 处理民警
	private String  clmj;
	// 备注
	private String  bz;
	// 奖惩情况
	private String  jcqk;
	//  当月考核名次
	private String  dykhmc;
	// 亲情会见种类
	private String  qqhjzlId;
	private String  qqhjzlName;
	// 创建警官
	private String  cjjg;
	// 罪犯当前信息
	private CriminalBaseInfoEntityVo  baseInfo;
	private String processId;
	private String processState;
	private Boolean isBack;
	private Boolean isDelete;
	private String currentDelegate;
	// 数据来源   用于同步
	private String sjly;
	
	public Integer getHjrsl() {
		return hjrsl;
	}
	public void setHjrsl(Integer hjrsl) {
		this.hjrsl = hjrsl;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getHjsj() {
		return hjsj;
	}
	public void setHjsj(Date hjsj) {
		this.hjsj = hjsj;
	}
	public String getHjyy() {
		return hjyy;
	}
	public void setHjyy(String hjyy) {
		this.hjyy = hjyy;
	}
	public String getClmj() {
		return clmj;
	}
	public void setClmj(String clmj) {
		this.clmj = clmj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getJcqk() {
		return jcqk;
	}
	public void setJcqk(String jcqk) {
		this.jcqk = jcqk;
	}
	public String getDykhmc() {
		return dykhmc;
	}
	public void setDykhmc(String dykhmc) {
		this.dykhmc = dykhmc;
	}
	public String getQqhjzlId() {
		return qqhjzlId;
	}
	public void setQqhjzlId(String qqhjzlId) {
		this.qqhjzlId = qqhjzlId;
	}
	public String getQqhjzlName() {
		return qqhjzlName;
	}
	public void setQqhjzlName(String qqhjzlName) {
		this.qqhjzlName = qqhjzlName;
	}
	public String getCjjg() {
		return cjjg;
	}
	public void setCjjg(String cjjg) {
		this.cjjg = cjjg;
	}
	public CriminalBaseInfoEntityVo getBaseInfo() {
		return baseInfo;
	}
	public void setBaseInfo(CriminalBaseInfoEntityVo baseInfo) {
		this.baseInfo = baseInfo;
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
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	
}
