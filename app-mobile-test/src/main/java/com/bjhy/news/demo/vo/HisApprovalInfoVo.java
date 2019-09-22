package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 历史审核意见Vo
 * @author cxh
 *
 */

public class HisApprovalInfoVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2176559106137218270L;
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 流程定义KEY
	 */
	private String ywb;
	/**
	 * 流程实例ID
	 */
	private String ywId;
	/**
	 * 审核人的用户ID
	 */
	private String shrId;
	/**
	 * 审核人的登录用户名
	 */
	private String shr;
	/**
	 * 审核人姓名
	 */
	private String xm;
	/**
	 * 审核意见信息
	 */
	private String shxx;
	/**
	 * 审核节点名称
	 */
	private String shjd;
	/**
	 * 审核节点ID
	 */
	private String shjdId;
	/**
	 * 审核类型(通过、不通过)
	 */
	private Boolean shlx;
	/**
	 * 审核时间
	 */
	private Date shsj;
	/**
	 * 创建时间
	 */
	private Date cjsj;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getYwb() {
		return ywb;
	}
	public void setYwb(String ywb) {
		this.ywb = ywb;
	}
	public String getYwId() {
		return ywId;
	}
	public void setYwId(String ywId) {
		this.ywId = ywId;
	}
	public String getShrId() {
		return shrId;
	}
	public void setShrId(String shrId) {
		this.shrId = shrId;
	}
	public String getShr() {
		return shr;
	}
	public void setShr(String shr) {
		this.shr = shr;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getShxx() {
		return shxx;
	}
	public void setShxx(String shxx) {
		this.shxx = shxx;
	}
	public String getShjd() {
		return shjd;
	}
	public void setShjd(String shjd) {
		this.shjd = shjd;
	}
	public String getShjdId() {
		return shjdId;
	}
	public void setShjdId(String shjdId) {
		this.shjdId = shjdId;
	}
	public Boolean getShlx() {
		return shlx;
	}
	public void setShlx(Boolean shlx) {
		this.shlx = shlx;
	}
	public Date getShsj() {
		return shsj;
	}
	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	
}
