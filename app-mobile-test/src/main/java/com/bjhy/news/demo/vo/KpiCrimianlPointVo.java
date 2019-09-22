package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 对应com.bjhy.criminal.core.domain.KpiCrimianlPoint
 * @author wlh
 *
 */
public class KpiCrimianlPointVo implements Serializable {
	
	private static final long serialVersionUID = 6023065792589457482L;
	private String id;
	private Date createDate;
	// 罪犯id
	private String zfid;
	// 创建人
	private String policeId;
	private String policeName;
	// 总考核分
	private Double nowpoint;
	// 历史考核分
	private Double oldpoint;
	// 总教育基础分
	private Double nowjybasepoint;
	// 总教育加分
	private Double nowjyaddpoint;
	// 总教育扣分
	private Double nowjylostpoint;
	// 总劳动基础分
	private Double nowldbasepoint;
	// 总劳动加分
	private Double nowldaddpoint;
	// 总劳动扣分
	private Double nowldlostpoint;
	// 总专项加分
	private Double nowzxaddpoint;
	// 总其它加分
	private Double nowqtaddpoint;
	// 总其它扣分
	private Double nowqtlostpoint;
	// 确认上月分数时间
	private Date subUpMonth;
	// 上次行政奖励的分数扣除时间
	private Date converDate;
	// 记功个数
	private Integer jgcount;
	// 表扬个数
	private Integer bycount;
	// 物资奖励个数
	private Integer wzjlcount;
	// 使用类型
	private String useTupeId;
	private String useTupeName;
	// 编辑原因
	private String bjyy;
	// 使用原因
	private String syyy;

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

	public String getZfid() {
		return zfid;
	}

	public void setZfid(String zfid) {
		this.zfid = zfid;
	}

	public String getPoliceId() {
		return policeId;
	}

	public void setPoliceId(String policeId) {
		this.policeId = policeId;
	}

	public String getPoliceName() {
		return policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	public Double getNowpoint() {
		return nowpoint;
	}

	public void setNowpoint(Double nowpoint) {
		this.nowpoint = nowpoint;
	}

	public Double getOldpoint() {
		return oldpoint;
	}

	public void setOldpoint(Double oldpoint) {
		this.oldpoint = oldpoint;
	}

	public Double getNowjybasepoint() {
		return nowjybasepoint;
	}

	public void setNowjybasepoint(Double nowjybasepoint) {
		this.nowjybasepoint = nowjybasepoint;
	}

	public Double getNowjyaddpoint() {
		return nowjyaddpoint;
	}

	public void setNowjyaddpoint(Double nowjyaddpoint) {
		this.nowjyaddpoint = nowjyaddpoint;
	}

	public Double getNowjylostpoint() {
		return nowjylostpoint;
	}

	public void setNowjylostpoint(Double nowjylostpoint) {
		this.nowjylostpoint = nowjylostpoint;
	}

	public Double getNowldbasepoint() {
		return nowldbasepoint;
	}

	public void setNowldbasepoint(Double nowldbasepoint) {
		this.nowldbasepoint = nowldbasepoint;
	}

	public Double getNowldaddpoint() {
		return nowldaddpoint;
	}

	public void setNowldaddpoint(Double nowldaddpoint) {
		this.nowldaddpoint = nowldaddpoint;
	}

	public Double getNowldlostpoint() {
		return nowldlostpoint;
	}

	public void setNowldlostpoint(Double nowldlostpoint) {
		this.nowldlostpoint = nowldlostpoint;
	}

	public Double getNowzxaddpoint() {
		return nowzxaddpoint;
	}

	public void setNowzxaddpoint(Double nowzxaddpoint) {
		this.nowzxaddpoint = nowzxaddpoint;
	}

	public Double getNowqtaddpoint() {
		return nowqtaddpoint;
	}

	public void setNowqtaddpoint(Double nowqtaddpoint) {
		this.nowqtaddpoint = nowqtaddpoint;
	}

	public Double getNowqtlostpoint() {
		return nowqtlostpoint;
	}

	public void setNowqtlostpoint(Double nowqtlostpoint) {
		this.nowqtlostpoint = nowqtlostpoint;
	}

	public Date getSubUpMonth() {
		return subUpMonth;
	}

	public void setSubUpMonth(Date subUpMonth) {
		this.subUpMonth = subUpMonth;
	}

	public Date getConverDate() {
		return converDate;
	}

	public void setConverDate(Date converDate) {
		this.converDate = converDate;
	}

	public Integer getJgcount() {
		return jgcount;
	}

	public void setJgcount(Integer jgcount) {
		this.jgcount = jgcount;
	}

	public Integer getBycount() {
		return bycount;
	}

	public void setBycount(Integer bycount) {
		this.bycount = bycount;
	}

	public Integer getWzjlcount() {
		return wzjlcount;
	}

	public void setWzjlcount(Integer wzjlcount) {
		this.wzjlcount = wzjlcount;
	}

	public String getUseTupeId() {
		return useTupeId;
	}

	public void setUseTupeId(String useTupeId) {
		this.useTupeId = useTupeId;
	}

	public String getUseTupeName() {
		return useTupeName;
	}

	public void setUseTupeName(String useTupeName) {
		this.useTupeName = useTupeName;
	}

	public String getBjyy() {
		return bjyy;
	}

	public void setBjyy(String bjyy) {
		this.bjyy = bjyy;
	}

	public String getSyyy() {
		return syyy;
	}

	public void setSyyy(String syyy) {
		this.syyy = syyy;
	}

}
