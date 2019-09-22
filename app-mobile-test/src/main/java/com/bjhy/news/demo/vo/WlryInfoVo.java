package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;




public class WlryInfoVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1536669226539995401L;

	private String id;

	private Date createDate;
	//申请单位
	private String sqdw;
	// 姓名
	private String  xm;
	// 性别
	private String  xb;
	// 职业
	private String  zy;
	// 证件类型code
	private String  zjlxId;
	// 证件类型名字
	private String  zjlxName;
	// 证件号码
	private String  zjhm;
	// 出生日期
	private Date  csrq;
	// 进入时间
	private Date  jrsj;
	// 离开时间
	private Date  lksj;
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
	// 住址
	private String  zz;
	// 照片
	private String  zp;
	//年龄
	private String  age;
	//备注
	private String bz;
	//申请进出理由
	private String sqjcly;
	
	// 创建警官
	private String  column_17;
	
	
	public String getColumn_17() {
		return column_17;
	}
	public void setColumn_17(String column_17) {
		this.column_17 = column_17;
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
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getPtmjjh() {
		return ptmjjh;
	}
	public void setPtmjjh(String ptmjjh) {
		this.ptmjjh = ptmjjh;
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
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getZjlxId() {
		return zjlxId;
	}
	public void setZjlxId(String zjlxId) {
		this.zjlxId = zjlxId;
	}
	public String getZjlxName() {
		return zjlxName;
	}
	public void setZjlxName(String zjlxName) {
		this.zjlxName = zjlxName;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
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
	public Date getLksj() {
		return lksj;
	}
	public void setLksj(Date lksj) {
		this.lksj = lksj;
	}
	public String getPtmj() {
		return ptmj;
	}
	public void setPtmj(String ptmj) {
		this.ptmj = ptmj;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
}
