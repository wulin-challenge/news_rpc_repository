package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 外来车辆
 * @author xiaozhou
 *
 */
public class ForeignVehicleApprovalVo implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 3804295658596236918L;

	private String id;
	
	private Date createDate;
	
	// 司乘人员姓名
	private String  scrxm;
	// 出生日期
	private Date  csrq;
	// 年龄
	private Integer  age;
	// 身份证号
	private String  sfzh;
	// 职业
	private String  zy;
	// 车牌号
	private String  cph;
	// 车型
	private String  cx;
	// 驾驶证号
	private String  jszh;
	// 进入时间
	private Date  jrsj;
	// 出去时间
	private Date  cqsj;
	// 申请进出理由
	private String  sqjcly;
	// 申请单位/民警
	private String  sqdw;
	// 陪同民警
	private String  ptmj;
	//陪同民警警号
	private String ptmjjh;
	//申请人姓名
	private String sqrxm;
	//申请人警号
	private String sqrjh;
	// 备注
	private String  bz;
	// 创建警官
	private String  cjjg;
	// 其他证件号
	private String  qtzjh;
	// 证件类型
	private String  zjlx;
	// 登记状态  0. 未登记 1. 已登记 2. 已出门
	private String  djzt;
	
	private String processId;
	private String processState;
	private Boolean isBack;
	private Boolean isDelete;
	private String currentDelegate;
	
	private String sjly ;
	private String assignee; //签收人
	// 是否作废
	private Boolean  sfzf;
	// 作废依据
	private String  zfyj;
	
	
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
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getPtmjjh() {
		return ptmjjh;
	}
	public void setPtmjjh(String ptmjjh) {
		this.ptmjjh = ptmjjh;
	}
	public String getSqrxm() {
		return sqrxm;
	}
	public void setSqrxm(String sqrxm) {
		this.sqrxm = sqrxm;
	}
	public String getSqrjh() {
		return sqrjh;
	}
	public void setSqrjh(String sqrjh) {
		this.sqrjh = sqrjh;
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
	public String getScrxm() {
		return scrxm;
	}
	public void setScrxm(String scrxm) {
		this.scrxm = scrxm;
	}
	public Date getCsrq() {
		return csrq;
	}
	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getCph() {
		return cph;
	}
	public void setCph(String cph) {
		this.cph = cph;
	}
	public String getCx() {
		return cx;
	}
	public void setCx(String cx) {
		this.cx = cx;
	}
	public String getJszh() {
		return jszh;
	}
	public void setJszh(String jszh) {
		this.jszh = jszh;
	}
	public Date getJrsj() {
		return jrsj;
	}
	public void setJrsj(Date jrsj) {
		this.jrsj = jrsj;
	}
	public Date getCqsj() {
		return cqsj;
	}
	public void setCqsj(Date cqsj) {
		this.cqsj = cqsj;
	}
	public String getSqjcly() {
		return sqjcly;
	}
	public void setSqjcly(String sqjcly) {
		this.sqjcly = sqjcly;
	}
	public String getSqdw() {
		return sqdw;
	}
	public void setSqdw(String sqdw) {
		this.sqdw = sqdw;
	}
	public String getPtmj() {
		return ptmj;
	}
	public void setPtmj(String ptmj) {
		this.ptmj = ptmj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCjjg() {
		return cjjg;
	}
	public void setCjjg(String cjjg) {
		this.cjjg = cjjg;
	}
	public String getQtzjh() {
		return qtzjh;
	}
	public void setQtzjh(String qtzjh) {
		this.qtzjh = qtzjh;
	}
	public String getZjlx() {
		return zjlx;
	}
	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}
	public String getDjzt() {
		return djzt;
	}
	public void setDjzt(String djzt) {
		this.djzt = djzt;
	}
	public Boolean getSfzf() {
		return sfzf;
	}
	public void setSfzf(Boolean sfzf) {
		this.sfzf = sfzf;
	}
	public String getZfyj() {
		return zfyj;
	}
	public void setZfyj(String zfyj) {
		this.zfyj = zfyj;
	}
	
}
