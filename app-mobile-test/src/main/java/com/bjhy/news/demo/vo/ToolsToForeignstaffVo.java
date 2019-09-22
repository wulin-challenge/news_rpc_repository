package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;


public class ToolsToForeignstaffVo implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = -7676533722660628883L;

private String id;
	
	private Date createDate;
	//工具名称
	private String name;
	//工具时效性   临时工具或长期工具
	private String sxx;
	//数量
	private String num;
	//工具用途
	private String yt;
	// 照片路径
	private String  zp;
	//指向对应外来人员的存储信息
	private ForeignStaffApprovalEntityVo foreignStaff;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSxx() {
		return sxx;
	}
	public void setSxx(String sxx) {
		this.sxx = sxx;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getYt() {
		return yt;
	}
	public void setYt(String yt) {
		this.yt = yt;
	}
	public String getZp() {
		return zp;
	}
	public void setZp(String zp) {
		this.zp = zp;
	}
	public ForeignStaffApprovalEntityVo getForeignStaff() {
		return foreignStaff;
	}
	public void setForeignStaff(ForeignStaffApprovalEntityVo foreignStaff) {
		this.foreignStaff = foreignStaff;
	}
}
