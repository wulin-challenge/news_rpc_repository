package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;


/**
 * 外协人员相同生产区
 * @author chenxuanhua
 *
 */
public class WxryInfoVo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1164696855096403813L;

	private String id;

	private Date createDate;
	
	// 姓名
	private String  xm;
	// 性别
	private String  xb;
	// 民族code_mz
	private String  mzId;
	private String  mzName;
	// 文化程度code_xl
	private String  whcdId;
	private String  whcdName;
	// 需进出的监管、生产区
	private String  jcscq;
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
	// 住址
	private String  zz;
	//备注
	private String bz;
	// 照片路径
	private String  zp;
	//年龄
	private String  age;
	// 创建警官
	private String  column_17;
	
	public String getColumn_17() {
		return column_17;
	}
	public void setColumn_17(String column_17) {
		this.column_17 = column_17;
	}
	
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	//指向对应单位的存储信息
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
