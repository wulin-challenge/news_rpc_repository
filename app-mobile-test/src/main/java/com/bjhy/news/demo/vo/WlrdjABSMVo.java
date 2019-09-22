package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;


public class WlrdjABSMVo  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4313069999857529082L;

	private String id;

	private Date createDate;
	/**
	 * 外来人进出AB门
	 */
	private WlrdjABEntityVo wlrdjab;
	// 值班民警
	private String  zbmj;
	// 值班民警警号
	private String  zbmjjh;
	// 陪同民警
	private String  ptmj;
	// 陪同民警警号
	private String  ptmjjh;
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
	public WlrdjABEntityVo getWlrdjab() {
		return wlrdjab;
	}
	public void setWlrdjab(WlrdjABEntityVo wlrdjab) {
		this.wlrdjab = wlrdjab;
	}
	public String getZbmj() {
		return zbmj;
	}
	public void setZbmj(String zbmj) {
		this.zbmj = zbmj;
	}
	public String getZbmjjh() {
		return zbmjjh;
	}
	public void setZbmjjh(String zbmjjh) {
		this.zbmjjh = zbmjjh;
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
	
}
