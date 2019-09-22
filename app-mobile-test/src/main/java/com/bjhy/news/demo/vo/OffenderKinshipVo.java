package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;


/**
 * 罪犯亲属VO  罪犯社会关系数据库实体VO
 * @author cxh
 *
 */
public class OffenderKinshipVo implements Serializable {
	
	private static final long serialVersionUID = 4075420548595200874L;
	private String id;
	private String criminalBaseInfoId;//罪犯基本信息id OK
	private String gxlbId   ;//关系类别 OK
	private String gxlbName;//关系类别名称 OK
	private String cw   ;//		称谓 OK
	private String xm   ;//		姓名 OK
	private String xb   ;//		性别 OK
	private Date   csrq ;//		出生日期 OK
	private String bqmmId   ;//		政治面貌  OK
	private String bqmmName;//政治面貎名称  OK
	private String zjlxId ;//证件类型  OK
	private String zjlxName;//证件类型名称 OK 
	private String zjhm ;//		证件号码 OK
	private String yzbm ;//		家庭住址邮政编码  OK
	private String familyProvinceId;//家庭地址省id OK
	private String familyProvinceName;//家庭地址省名称 OK
	private String familyCityId;//家庭地址市ID OK
	private String familyCityName;//家庭地址市名称 OK
	private String familyAddressId;//家庭地址区县ID OK
	private String familyAddressName;//家庭地址区县名称 OK 
	private String familyAddressDetail;//存储家庭地址的详情OK 
	private String jdtzxx;//存储家庭地址的详情


	private String unitProvinceId;//单位详细地省id OK
	private String unitProvinceName;//单位详细地省名称 OK
	private String unitCityId;//单位详细地市ID OK
	private String unitCityName;//单位详细地市名称 OK
	private String unitAddressId;//单位详细地区县ID OK
	private String unitAddressName;//单位详细地区县名称 OK
	private String unitAddressDetail ;//单位详细地址 OK
	private String dwmx ;//单位（详细名称）
	private String dh   ;//联系电话 OK
	private Boolean isQqdh ;//是否是亲情电话 OK
	private Boolean isZlxr ;//是否主联系人 OK
	private String zyId   ;//职业  OK
	private String zyName;//职业名称  OK
	
	//司法部上传添加==>>
	//职务Id
	private String zwId;
	//<<==司法部上传添加
	
	private String zw   ;//职务
	private Boolean isSh   ;//是否审核 OK
	private String bz   ;//备注 OK
	private Date createDate;//创建时间 OK
	private String dangkaid;//档卡id
	private String sjly;//数据来源
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCriminalBaseInfoId() {
		return criminalBaseInfoId;
	}
	public void setCriminalBaseInfoId(String criminalBaseInfoId) {
		this.criminalBaseInfoId = criminalBaseInfoId;
	}
	public String getGxlbId() {
		return gxlbId;
	}
	public void setGxlbId(String gxlbId) {
		this.gxlbId = gxlbId;
	}
	public String getGxlbName() {
		return gxlbName;
	}
	public void setGxlbName(String gxlbName) {
		this.gxlbName = gxlbName;
	}
	public String getCw() {
		return cw;
	}
	public void setCw(String cw) {
		this.cw = cw;
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
	public Date getCsrq() {
		return csrq;
	}
	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}
	public String getBqmmId() {
		return bqmmId;
	}
	public void setBqmmId(String bqmmId) {
		this.bqmmId = bqmmId;
	}
	public String getBqmmName() {
		return bqmmName;
	}
	public void setBqmmName(String bqmmName) {
		this.bqmmName = bqmmName;
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
	public String getYzbm() {
		return yzbm;
	}
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
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
	public String getFamilyAddressId() {
		return familyAddressId;
	}
	public void setFamilyAddressId(String familyAddressId) {
		this.familyAddressId = familyAddressId;
	}
	public String getFamilyAddressName() {
		return familyAddressName;
	}
	public void setFamilyAddressName(String familyAddressName) {
		this.familyAddressName = familyAddressName;
	}
	public String getFamilyAddressDetail() {
		return familyAddressDetail;
	}
	public void setFamilyAddressDetail(String familyAddressDetail) {
		this.familyAddressDetail = familyAddressDetail;
	}
	public String getJdtzxx() {
		return jdtzxx;
	}
	public void setJdtzxx(String jdtzxx) {
		this.jdtzxx = jdtzxx;
	}
	public String getUnitProvinceId() {
		return unitProvinceId;
	}
	public void setUnitProvinceId(String unitProvinceId) {
		this.unitProvinceId = unitProvinceId;
	}
	public String getUnitProvinceName() {
		return unitProvinceName;
	}
	public void setUnitProvinceName(String unitProvinceName) {
		this.unitProvinceName = unitProvinceName;
	}
	public String getUnitCityId() {
		return unitCityId;
	}
	public void setUnitCityId(String unitCityId) {
		this.unitCityId = unitCityId;
	}
	public String getUnitCityName() {
		return unitCityName;
	}
	public void setUnitCityName(String unitCityName) {
		this.unitCityName = unitCityName;
	}
	public String getUnitAddressId() {
		return unitAddressId;
	}
	public void setUnitAddressId(String unitAddressId) {
		this.unitAddressId = unitAddressId;
	}
	public String getUnitAddressName() {
		return unitAddressName;
	}
	public void setUnitAddressName(String unitAddressName) {
		this.unitAddressName = unitAddressName;
	}
	public String getUnitAddressDetail() {
		return unitAddressDetail;
	}
	public void setUnitAddressDetail(String unitAddressDetail) {
		this.unitAddressDetail = unitAddressDetail;
	}
	public String getDwmx() {
		return dwmx;
	}
	public void setDwmx(String dwmx) {
		this.dwmx = dwmx;
	}
	public String getDh() {
		return dh;
	}
	public void setDh(String dh) {
		this.dh = dh;
	}
	public Boolean getIsQqdh() {
		return isQqdh;
	}
	public void setIsQqdh(Boolean isQqdh) {
		this.isQqdh = isQqdh;
	}
	public Boolean getIsZlxr() {
		return isZlxr;
	}
	public void setIsZlxr(Boolean isZlxr) {
		this.isZlxr = isZlxr;
	}
	public String getZyId() {
		return zyId;
	}
	public void setZyId(String zyId) {
		this.zyId = zyId;
	}
	public String getZyName() {
		return zyName;
	}
	public void setZyName(String zyName) {
		this.zyName = zyName;
	}
	public String getZwId() {
		return zwId;
	}
	public void setZwId(String zwId) {
		this.zwId = zwId;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	public Boolean getIsSh() {
		return isSh;
	}
	public void setIsSh(Boolean isSh) {
		this.isSh = isSh;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getDangkaid() {
		return dangkaid;
	}
	public void setDangkaid(String dangkaid) {
		this.dangkaid = dangkaid;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	
}
