package com.bjhy.news.demo.api;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.bjhy.news.demo.vo.ForeignStaffApprovalEntityVo;
import com.bjhy.news.demo.vo.ToolsToForeignstaffVo;
import com.bjhy.news.demo.vo.WlrdjABEntityVo;
import com.bjhy.news.demo.vo.WlryInfoVo;
import com.bjhy.platform.commons.jqgrid.QueryParams;
import com.bjhy.platform.commons.pager.PageBean;

/**
 * 外来人员进出AB门(进出APP后台提供数据接口)
 * 
 */
public interface WlryABService {
	
	/**
	 * 待办列表
	 * @param pagebean
	 * @return
	 */
	public PageBean getInAndOutApplyNotBeenDoneList(PageBean pagebean,String userName);
	
	/**
	 * 已办列表
	 * @param pageBean
	 * @return
	 */
	public PageBean getInAndOutApplyBeenDoneList(PageBean pageBean);
	
	
	/**
	 * 获取申请单位列表     	
	 * @param object
	 * @return
	 */
//	public List<String> getApplicantCompanyList();
	
	
	/**
	 * 保存申请
	 * @param object（外来人员实体）
	 * @param list2（多人外来人员实体）
	 * @param vo（外来人员所带工具实体集合）
	 * @return
	 */
	public String saveInAndOutApply(ForeignStaffApprovalEntityVo object,List<WlryInfoVo>listWlry,List<ToolsToForeignstaffVo> list);
	
	/**
	 * 保存并送审
	 * @param object（外来人员实体）
	 * @param list2（多人外来人员实体）
	 * @param list（外来人员工具实体集合）
	 */
	public void submitInAndOutApply(ForeignStaffApprovalEntityVo object,List<WlryInfoVo>listWlry,List<ToolsToForeignstaffVo> list,String userName);
	
	/**
	 * 通过外来人员id，获取工具信息
	 * @param id
	 */
	public List<ToolsToForeignstaffVo> getGjinfoById(String id);
	
	/**
	 * 获取审批详情
	 * @param id
	 * @return
	 */
	public Map<String, Object> getInAndOutApplyById(String id);
	
	/**
	 * 获取进出门详情
	 * @param id
	 * @return
	 */
	public Map<String, Object> getJcmxxById(String id);
	
	/**
	 * 办理/回退审批
	 * @param id
	 * @param approvalInfo   
	 * @param boo   是办理还是回退    true（办理）  false（回退）
	 */
	public void disposeOrRollback(String id,String approvalInfo,boolean boo,String userName);

	/**
	 * 当日进出门登记列表
	 * @param pageBean
	 * @return
	 * @throws ParseException
	 */
	public PageBean getInAndOutRegisterList(PageBean pageBean)throws ParseException;
	
	/**
	 * 已归档申请列表
	 * @param pageBean
	 * @return
	 */
	public PageBean getInAndOutApplyArchived(PageBean pageBean);
	
	/**
	 * 生成进门登记
	 * @param object  进入AB门实体
	 * @param ids		外来人员业务id（数组）
	 */
	public Map<String, Object> inDoorRegister( String id,List<WlryInfoVo>list1,
	List<ToolsToForeignstaffVo>list2,WlrdjABEntityVo object);
	
	/**
	 * 生成出门登记
	 * @param id  出AB门主键id，多个以逗号连接
	 * @param wlrdjAB
	 */
	public void outDoorRegister(String id,List<WlryInfoVo>list1,
			List<ToolsToForeignstaffVo>list2, WlrdjABEntityVo wlrdjAB);
	
	/**
	 * 扫码对接
	 * @param wlrdjABId   进出AB门登记id
	 * @param ajmjxm	    安检民警姓名
	 * @param ptmjxm	    陪同民警姓名
	 * @param ajmjjh	    安检民警警号
	 * @param ptmjjh      陪同民警警号
	 */
	public void scanCodeButtJoint(String wlrdjABId,String zbmjxm,String ptmjxm,String zbmjjh,String ptmjjh);
	
	/**
	 * 获取证件类型和name
	 * @param zjlxId
	 * @return
	 */
	List<Map<String, Object>> getZjlxAndName();
	
	/**
	 * 删除申请业务数据，参数为String[],可支持批量删除（新建状态）
	 * 
	 */
	void deleteList(String[] ids);
	/**
	 * 删除申请业务数据，参数为String[],可支持批量删除（申请填报状态）
	 * 
	 */
	void deleteListBiz(String[] ids);
}
