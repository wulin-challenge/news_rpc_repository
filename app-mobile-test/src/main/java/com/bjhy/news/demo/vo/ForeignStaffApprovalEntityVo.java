package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;



public class ForeignStaffApprovalEntityVo implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -5218635594759476721L;

	private String id;

	private Date createDate;
	
	// 外来人员姓名
	private String  wlrxm;
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
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date  csrq;
	// 进入时间
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date  jrsj;
	// 出去时间
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date  cqsj;
	// 申请进出理由
	private String  sqjcly;
	// 申请单位
	private String  sqdw;
	// 住址
	private String  zz;
	// 陪同民警
	private String  ptmj;
	//陪同民警警号
	private String  ptmjjh;
	// 陪同民警2
	private String  ptmj2;
	//陪同民警2警号
	private String  ptmjjh2;
	// 陪同民警3
	private String  ptmj3;
	//陪同民警3警号
	private String  ptmjjh3;
	//申请人姓名
	private String  sqrxm;
	//申请人警号
	private String  sqrjh;
	//监狱编号
	private String jybh;
	//批次号
	private String pch;
	// 照片路径
	private String  zp;
	// 登记状态
	private String  djzt;
	// 备注
	private String  bz;
	// 创建时间
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date  column_16;
	// 创建警官
	private String  column_17;
	// 是否作废
	private boolean  sfzf;
	// 作废依据
	private String  zfyj;
	
	private String sjly;
	
	
	
	private String processId;
	private String processState;
	private Boolean isBack;
	private Boolean isDelete;
	private String currentDelegate;
	private String assignee; //签收人
	
	
	public String getSqrxm() {
		return sqrxm;
	}

	public void setSqrxm(String sqrxm) {
		this.sqrxm = sqrxm;
	}

	public String getPtmj2() {
		return ptmj2;
	}

	public void setPtmj2(String ptmj2) {
		this.ptmj2 = ptmj2;
	}

	public String getPtmjjh2() {
		return ptmjjh2;
	}

	public void setPtmjjh2(String ptmjjh2) {
		this.ptmjjh2 = ptmjjh2;
	}

	public String getPtmj3() {
		return ptmj3;
	}

	public void setPtmj3(String ptmj3) {
		this.ptmj3 = ptmj3;
	}

	public String getPtmjjh3() {
		return ptmjjh3;
	}

	public void setPtmjjh3(String ptmjjh3) {
		this.ptmjjh3 = ptmjjh3;
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

	public String getPtmjjh() {
		return ptmjjh;
	}

	public void setPtmjjh(String ptmjjh) {
		this.ptmjjh = ptmjjh;
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
	
	public String getWlrxm() {
		return wlrxm;
	}
	public void setWlrxm(String wlrxm) {
		this.wlrxm = wlrxm;
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
	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
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
	public String getPtmj() {
		return ptmj;
	}
	public void setPtmj(String ptmj) {
		this.ptmj = ptmj;
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
		return column_16;
	}
	public void setColumn_16(Date column_16) {
		this.column_16 = column_16;
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
	public String getCurrentDelegate() {
		return currentDelegate;
	}
	public void setCurrentDelegate(String currentDelegate) {
		this.currentDelegate = currentDelegate;
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

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
	
}
