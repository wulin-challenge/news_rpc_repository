package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;



public class CydjdhAllocationEntityVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String cydjCodeId;
	private String cydjCodeName;
	//激励兑换code
	private String jldhCodeId;
	private String jldhCodeName;
	//单次兑换分数
	private Double score;
	//单次兑换次数
	private String cs;
	//项目内容时间或价值
	private String sj;
	//当月兑换上限
	private Integer exchangelimit;
	//详情
	private String xqName;
	//创建时间
	private Date createDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getJldhCodeId() {
		return jldhCodeId;
	}
	public void setJldhCodeId(String jldhCodeId) {
		this.jldhCodeId = jldhCodeId;
	}
	public String getJldhCodeName() {
		return jldhCodeName;
	}
	public void setJldhCodeName(String jldhCodeName) {
		this.jldhCodeName = jldhCodeName;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getCs() {
		return cs;
	}
	public void setCs(String cs) {
		this.cs = cs;
	}
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj = sj;
	}
	public Integer getExchangelimit() {
		return exchangelimit;
	}
	public void setExchangelimit(Integer exchangelimit) {
		this.exchangelimit = exchangelimit;
	}
	
	public String getXqName() {
		return xqName;
	}
	public void setXqName(String xqName) {
		this.xqName = xqName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCydjCodeId() {
		return cydjCodeId;
	}
	public void setCydjCodeId(String cydjCodeId) {
		this.cydjCodeId = cydjCodeId;
	}
	public String getCydjCodeName() {
		return cydjCodeName;
	}
	public void setCydjCodeName(String cydjCodeName) {
		this.cydjCodeName = cydjCodeName;
	}
}
