package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 外来车辆登记
 * @author
 *
 */
public class CldjEntityVo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5075571454655039276L;
	private String id;
	//创建日期
	private Date createDate;
	// 临时出入证号
	private String  lscrzh;
	// 进门值班民警
	private String  zbmj;
	// 进门陪同民警
	private String  ptmj;
	// 进门安检民警
	private String  ajmj;
	// 进门值班民警警号
	private String  zbmjjh;
	// 进门陪同民警警号
	private String  ptmjjh;
	// 进门安检民警警号
	private String  ajmjjh;
	//申请单位
	private String sqdw;
	// 进门时间
	private Date  jmsj;
	// 离开时间
	private Date  lksj;
	//出门值班民警
	private String cmzbmj;
	// 出门陪同民警
	private String  cmptmj;
	// 出门安检民警
	private String  cmajmj;
	//出门值班民警警号
	private String cmzbmjjh;
	// 出门陪同民警警号
	private String  cmptmjjh;
	// 出门安检民警警号
	private String  cmajmjjh;
	// 寄存物品
	private String  jcwp;
	// 是否领取物品
	private Boolean  lqwp;
	// 备注
	private String  bz;
	//车辆登记信息
	
	private ForeignVehicleApprovalVo wlclId;
	//提供app端的外来车辆申请id
	private String wlclyId;
	
	
	public String getWlclyId() {
		return wlclyId;
	}

	public void setWlclyId(String wlclyId) {
		this.wlclyId = wlclyId;
	}

	public String getCmzbmjjh() {
		return cmzbmjjh;
	}

	public void setCmzbmjjh(String cmzbmjjh) {
		this.cmzbmjjh = cmzbmjjh;
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

	public String getZbmjjh() {
		return zbmjjh;
	}

	public void setZbmjjh(String zbmjjh) {
		this.zbmjjh = zbmjjh;
	}

	public String getPtmjjh() {
		return ptmjjh;
	}

	public void setPtmjjh(String ptmjjh) {
		this.ptmjjh = ptmjjh;
	}

	public String getAjmjjh() {
		return ajmjjh;
	}

	public void setAjmjjh(String ajmjjh) {
		this.ajmjjh = ajmjjh;
	}

	public String getSqdw() {
		return sqdw;
	}

	public void setSqdw(String sqdw) {
		this.sqdw = sqdw;
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

	public String getLscrzh() {
		return lscrzh;
	}

	public void setLscrzh(String lscrzh) {
		this.lscrzh = lscrzh;
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

	public Date getLksj() {
		return lksj;
	}

	public void setLksj(Date lksj) {
		this.lksj = lksj;
	}

	public String getCmzbmj() {
		return cmzbmj;
	}

	public void setCmzbmj(String cmzbmj) {
		this.cmzbmj = cmzbmj;
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

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public ForeignVehicleApprovalVo getWlclId() {
		return wlclId;
	}

	public void setWlclId(ForeignVehicleApprovalVo wlclId) {
		this.wlclId = wlclId;
	}
		
}
