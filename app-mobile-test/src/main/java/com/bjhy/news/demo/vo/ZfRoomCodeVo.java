package com.bjhy.news.demo.vo;

import java.io.Serializable;

public class ZfRoomCodeVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 596185470316529653L;
	
	private String id;
	// 楼栋号
	private String  ldh;
	// 单元数
	private String  dys;
	// 层数
	private String  cs;
	// 房间数
	private Integer  fjs;
	//分数
	private String score;
	
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getCs() {
		return cs;
	}
	public void setCs(String cs) {
		this.cs = cs;
	}
	public Integer getFjs() {
		return fjs;
	}
	public void setFjs(Integer fjs) {
		this.fjs = fjs;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLdh() {
		return ldh;
	}
	public void setLdh(String ldh) {
		this.ldh = ldh;
	}
	public String getDys() {
		return dys;
	}
	public void setDys(String dys) {
		this.dys = dys;
	}
	
}
