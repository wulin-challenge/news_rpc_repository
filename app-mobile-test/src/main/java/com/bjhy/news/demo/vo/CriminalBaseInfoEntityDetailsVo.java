package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;


public class CriminalBaseInfoEntityDetailsVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5325942928651020388L;
	private String id;
	private String xm; // 姓名
	private String bh;
	private String gydwId; // 监区 code
	private String gydwName;//监区name
	private Date dqxqqr; //当前刑期起日 OK
	private Date dqxqzr;//当前刑期止日 OK
	private String familyProvinceId;//家庭省份ID OK
	private String familyProvinceName;//家庭省份名称 OK
	private String familyCityId;//家庭城市ID OK
	private String familyCityName;//家庭城市名称 OK
	private String familyAreaId;//家庭区ID OK
	private String familyAreaName;//家庭区名称 OK
	private String familyAddressDetail;//家庭地址详情
	
	
	public String getFamilyProvinceId() {
		return familyProvinceId;
	}
	public void setFamilyProvinceId(String familyProvinceId) {
		this.familyProvinceId = familyProvinceId;
	}
	public String getFamilyProvinceName() {
		return familyProvinceName;
	}
	public void setFamilyProvinceName(String familyProvinceName) {
		this.familyProvinceName = familyProvinceName;
	}
	public String getFamilyCityId() {
		return familyCityId;
	}
	public void setFamilyCityId(String familyCityId) {
		this.familyCityId = familyCityId;
	}
	public String getFamilyCityName() {
		return familyCityName;
	}
	public void setFamilyCityName(String familyCityName) {
		this.familyCityName = familyCityName;
	}
	public String getFamilyAreaId() {
		return familyAreaId;
	}
	public void setFamilyAreaId(String familyAreaId) {
		this.familyAreaId = familyAreaId;
	}
	public String getFamilyAreaName() {
		return familyAreaName;
	}
	public void setFamilyAreaName(String familyAreaName) {
		this.familyAreaName = familyAreaName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String getGydwId() {
		return gydwId;
	}
	public void setGydwId(String gydwId) {
		this.gydwId = gydwId;
	}
	public String getGydwName() {
		return gydwName;
	}
	public void setGydwName(String gydwName) {
		this.gydwName = gydwName;
	}
	public Date getDqxqqr() {
		return dqxqqr;
	}
	public void setDqxqqr(Date dqxqqr) {
		this.dqxqqr = dqxqqr;
	}
	public Date getDqxqzr() {
		return dqxqzr;
	}
	public void setDqxqzr(Date dqxqzr) {
		this.dqxqzr = dqxqzr;
	}
	public String getFamilyAddressDetail() {
		return familyAddressDetail;
	}
	public void setFamilyAddressDetail(String familyAddressDetail) {
		this.familyAddressDetail = familyAddressDetail;
	}
	
}
