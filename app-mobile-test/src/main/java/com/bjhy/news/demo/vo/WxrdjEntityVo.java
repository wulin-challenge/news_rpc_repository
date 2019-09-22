package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * AB门外协人员登记管理
 * @author wdq
 *
 */
public class WxrdjEntityVo implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8030654018989362286L;

	private String id;
	private String sqdw;

	private Date createDate;
	/**
	 *外协人
	 */
	private ForeignAidStaffApprovalEntityVo foreignAidStaff;
	
	// 值班民警
	private String  zbmj;
	// 值班民警警号
	private String  zbmjjh;
	// 安检民警警号
	private String  ajmjjh;
	// 陪同民警
	private String  ptmj;
	// 陪同民警警号
	private String  ptmjjh;
	// 安检民警
	private String  ajmj;
	// 进门时间
	private Date  jmsj;
	
	// 出门陪同民警
	private String  cmptmj;
	// 出门陪同民警警号
	private String  cmptmjjh;
	// 出门安检民警警号
	private String  cmajmjjh;
	// 出门安检民警
	private String  cmajmj;
	// 离开时间
	private Date  lksj;
	
	// 备注
	private String  bz;
	// 寄存物品
	private String  jcwp;
	// 是否领取物品
	private Boolean  lqwp;
	private String wxryId;
	
	public String getSqdw() {
		return sqdw;
	}
	public void setSqdw(String sqdw) {
		this.sqdw = sqdw;
	}
	public String getZbmjjh() {
		return zbmjjh;
	}
	public void setZbmjjh(String zbmjjh) {
		this.zbmjjh = zbmjjh;
	}
	public String getAjmjjh() {
		return ajmjjh;
	}
	public void setAjmjjh(String ajmjjh) {
		this.ajmjjh = ajmjjh;
	}
	public String getPtmjjh() {
		return ptmjjh;
	}
	public void setPtmjjh(String ptmjjh) {
		this.ptmjjh = ptmjjh;
	}
	public String getCmptmjjh() {
		return cmptmjjh;
	}
	public void setCmptmjjh(String cmptmjjh) {
		this.cmptmjjh = cmptmjjh;
	}
	public String getCmajmjjh() {
		return cmajmjjh;
	}
	public void setCmajmjjh(String cmajmjjh) {
		this.cmajmjjh = cmajmjjh;
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
	public ForeignAidStaffApprovalEntityVo getForeignAidStaff() {
		return foreignAidStaff;
	}
	public void setForeignAidStaff(ForeignAidStaffApprovalEntityVo foreignAidStaff) {
		this.foreignAidStaff = foreignAidStaff;
	}
	public String getZbmj() {
		return zbmj;
	}
	public void setZbmj(String zbmj) {
		this.zbmj = zbmj;
	}
	public String getPtmj() {
		return ptmj;
	}
	public void setPtmj(String ptmj) {
		this.ptmj = ptmj;
	}
	public String getAjmj() {
		return ajmj;
	}
	public void setAjmj(String ajmj) {
		this.ajmj = ajmj;
	}
	public Date getJmsj() {
		return jmsj;
	}
	public void setJmsj(Date jmsj) {
		this.jmsj = jmsj;
	}
	public String getCmptmj() {
		return cmptmj;
	}
	public void setCmptmj(String cmptmj) {
		this.cmptmj = cmptmj;
	}
	public String getCmajmj() {
		return cmajmj;
	}
	public void setCmajmj(String cmajmj) {
		this.cmajmj = cmajmj;
	}
	public Date getLksj() {
		return lksj;
	}
	public void setLksj(Date lksj) {
		this.lksj = lksj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getJcwp() {
		return jcwp;
	}
	public void setJcwp(String jcwp) {
		this.jcwp = jcwp;
	}
	public Boolean getLqwp() {
		return lqwp;
	}
	public void setLqwp(Boolean lqwp) {
		this.lqwp = lqwp;
	}
	public String getWxryId() {
		return wxryId;
	}
	public void setWxryId(String wxryId) {
		this.wxryId = wxryId;
	}
			
}
