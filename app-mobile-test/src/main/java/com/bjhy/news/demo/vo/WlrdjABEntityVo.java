package com.bjhy.news.demo.vo;

import java.io.Serializable;
import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;


public class WlrdjABEntityVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4875520048446789273L;

	private String id;

	private Date createDate;
	/**
	 * 外来人
	 */
	private ForeignStaffApprovalEntityVo foreignStaff;
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
		// 陪同民警2
		private String  ptmj2;
		// 陪同民警2警号
		private String  ptmjjh2;
		// 陪同民警3
		private String  ptmj3;
		// 陪同民警3警号
		private String  ptmjjh3;
		// 安检民警
		private String  ajmj;
		// 进门时间
		private Date  jmsj;
		
		// 出门陪同民警
		private String  cmptmj;
		// 出门陪同民警警号
		private String  cmptmjjh;
				
		// 出门安检民警
		private String  cmajmj;
		// 出门安检民警警号
		private String  cmajmjjh;
		// 离开时间
		private Date  lksj;
		//关联的id
		// 进入时间
		private Date  jrsj;
		// 出去时间
		private Date  cqsj;
		//记录标识
		private String sisid;
		//申请单位
		private String sqdw;
		// 寄存物品
		private String  jcwp;
		
		//提供给app用的 查询外来人员申请业务数据的id
		private String applyid;
		// 备注
		private String  bz;
		// 是否领取物品
		private Boolean  lqwp;
		private String wlryid;
		
		
		public String getApplyid() {
			return applyid;
		}
		public void setApplyid(String applyid) {
			this.applyid = applyid;
		}
		public String getWlryid() {
			return wlryid;
		}
		public void setWlryid(String wlryid) {
			this.wlryid = wlryid;
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
		public String getBz() {
			return bz;
		}
		public void setBz(String bz) {
			this.bz = bz;
		}
		public Boolean getLqwp() {
			return lqwp;
		}
		public void setLqwp(Boolean lqwp) {
			this.lqwp = lqwp;
		}
		public String getJcwp() {
			return jcwp;
		}
		public void setJcwp(String jcwp) {
			this.jcwp = jcwp;
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
		public String getPtmj2() {
			return ptmj2;
		}
		public void setPtmj2(String ptmj2) {
			this.ptmj2 = ptmj2;
		}
		public String getPtmjjh2() {
			return ptmjjh2;
		}
		public void setPtmjjh2(String ptmjjh2) {
			this.ptmjjh2 = ptmjjh2;
		}
		public String getPtmj3() {
			return ptmj3;
		}
		public void setPtmj3(String ptmj3) {
			this.ptmj3 = ptmj3;
		}
		public String getPtmjjh3() {
			return ptmjjh3;
		}
		public void setPtmjjh3(String ptmjjh3) {
			this.ptmjjh3 = ptmjjh3;
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
	public ForeignStaffApprovalEntityVo getForeignStaff() {
		return foreignStaff;
	}
	public void setForeignStaff(ForeignStaffApprovalEntityVo foreignStaff) {
		this.foreignStaff = foreignStaff;
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
	public Date getJrsj() {
		return jrsj;
	}
	public void setJrsj(Date jrsj) {
		this.jrsj = jrsj;
	}
	public Date getCqsj() {
		return cqsj;
	}
	public void setCqsj(Date cqsj) {
		this.cqsj = cqsj;
	}
	public String getSisid() {
		return sisid;
	}
	public void setSisid(String sisid) {
		this.sisid = sisid;
	}
	
	
}
