package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 外协人ForeignAidStaffApprovalEntity
 * @author 
 *
 */
public class ForeignAidStaffApprovalEntityVo implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1521936165700796074L;

	private String id;

	private Date createDate;
	
	// 外来人员姓名
	private String  wxrxm;
	// 性别
	private String  xb;
	// 年龄
	private String  age;
	// 职业
	private String  zy;
	// 证件类型	
	private String  jzlxId;
	private String  jzlxName;
	// 身份证号
	private String  sfzh;
	// 出生日期	
	private Date  csrq;
	private String  mzId;
	private String  mzName;
	// 文化程度code_xl	
	private String  whcdId;
	private String  whcdName;
	// 需进出的监管、生产区
	private String  jcscq;
	// 进入时间	
	private Date  jrsj;
	// 出去时间	
	private Date  cqsj;
	// 申请进出理由
	private String  sqjcly;
	// 申请单位
	private String  sqdw;
	// 住址
	private String  zz;
	// 照片路径
	private String  zp;
	// 登记状态
	private String  djzt;
	// 备注
	private String  bz;
	// 创建时间	
	private Date  Column_16;
	// 创建警官
	private String  column_17;
	
	//为app添加陪同民警和警号字段   pc端后续会加上字段
	// 陪同民警 外协只有一个人
	private String  ptmj;
	//陪同民警警号
	private String  ptmjjh;
	//申请人姓名
	private String  sqrxm;
	//申请人警号
	private String  sqrjh;
	//监狱编号
	private String jybh;
	//批次号
	private String pch;
		
	// 是否作废
	private boolean  sfzf;
	// 作废依据
	private String  zfyj;
	// 临时出入证号
	private String  lscrzh;
	private String processId;
	private String processState;
	private Boolean isBack;
	private Boolean isDelete;
	private String currentDelegate;
	private String assignee; //签收人
	
	//数据来源
	private String sjly ;
	
	
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
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	public String getPtmj() {
		return ptmj;
	}
	public void setPtmj(String ptmj) {
		this.ptmj = ptmj;
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
	public String getJybh() {
		return jybh;
	}
	public void setJybh(String jybh) {
		this.jybh = jybh;
	}
	public String getPch() {
		return pch;
	}
	public void setPch(String pch) {
		this.pch = pch;
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
	public String getWxrxm() {
		return wxrxm;
	}
	public void setWxrxm(String wxrxm) {
		this.wxrxm = wxrxm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getJzlxId() {
		return jzlxId;
	}
	public void setJzlxId(String jzlxId) {
		this.jzlxId = jzlxId;
	}
	public String getJzlxName() {
		return jzlxName;
	}
	public void setJzlxName(String jzlxName) {
		this.jzlxName = jzlxName;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public Date getCsrq() {
		return csrq;
	}
	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}
	public String getMzId() {
		return mzId;
	}
	public void setMzId(String mzId) {
		this.mzId = mzId;
	}
	public String getMzName() {
		return mzName;
	}
	public void setMzName(String mzName) {
		this.mzName = mzName;
	}
	public String getWhcdId() {
		return whcdId;
	}
	public void setWhcdId(String whcdId) {
		this.whcdId = whcdId;
	}
	public String getWhcdName() {
		return whcdName;
	}
	public void setWhcdName(String whcdName) {
		this.whcdName = whcdName;
	}
	public String getJcscq() {
		return jcscq;
	}
	public void setJcscq(String jcscq) {
		this.jcscq = jcscq;
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
	public String getZz() {
		return zz;
	}
	public void setZz(String zz) {
		this.zz = zz;
	}
	public String getZp() {
		return zp;
	}
	public void setZp(String zp) {
		this.zp = zp;
	}
	public String getDjzt() {
		return djzt;
	}
	public void setDjzt(String djzt) {
		this.djzt = djzt;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Date getColumn_16() {
		return Column_16;
	}
	public void setColumn_16(Date column_16) {
		Column_16 = column_16;
	}
	public String getColumn_17() {
		return column_17;
	}
	public void setColumn_17(String column_17) {
		this.column_17 = column_17;
	}
	public boolean isSfzf() {
		return sfzf;
	}
	public void setSfzf(boolean sfzf) {
		this.sfzf = sfzf;
	}
	public String getZfyj() {
		return zfyj;
	}
	public void setZfyj(String zfyj) {
		this.zfyj = zfyj;
	}
	public String getLscrzh() {
		return lscrzh;
	}
	public void setLscrzh(String lscrzh) {
		this.lscrzh = lscrzh;
	}
				
	
}
