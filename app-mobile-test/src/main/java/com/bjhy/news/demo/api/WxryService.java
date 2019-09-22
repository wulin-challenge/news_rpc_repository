package com.bjhy.news.demo.api;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.bjhy.news.demo.vo.ForeignAidStaffApprovalEntityVo;
import com.bjhy.news.demo.vo.ToolsToForeignAidstaffVo;
import com.bjhy.news.demo.vo.ToolsToForeignstaffVo;
import com.bjhy.news.demo.vo.WxrdjEntityVo;
import com.bjhy.news.demo.vo.WxryInfoVo;
import com.bjhy.platform.commons.pager.PageBean;

public interface WxryService {

	/**
	 * 待办列表
	 * @param pagebean
	 * @return
	 */
	public PageBean getInAndOutApplyNotBeenDoneList(PageBean pageBean,String userName);
	
	/**
	 * 已办列表
	 * @param pageBean
	 * @return
	 */
	public PageBean getInAndOutApplyBeenDoneList(PageBean pageBean);
				
	/**
	 * 保存申请
	 * @param object（外协人员实体）
	 * @param list2（多人外协人员实体）
	 * @return
	 */
	public String saveInAndOutApply(ForeignAidStaffApprovalEntityVo object, List<WxryInfoVo> wxryList,
			List<ToolsToForeignAidstaffVo> list);
	
	/**
	 * 保存并送审
	 * @param object（外来人员实体）
	 * @param list2（多人外来人员实体）
	 * @param list（外来人员工具实体集合）
	 */
	public void submitInAndOutApply(ForeignAidStaffApprovalEntityVo object, List<WxryInfoVo> wxryList,
			List<ToolsToForeignAidstaffVo> list);
	
	/**
	 * 通过外协人员id，获取工具信息
	 * @param id
	 */
	public List<ToolsToForeignAidstaffVo> getGjinfoById(String id);
	
	/**
	 * 获取审批详情
	 * @param id
	 * @return
	 */
	public Map<String, Object> getInAndOutApplyById(String id);
	
	
	/**
	 * 办理/回退审批
	 * @param id
	 * @param approvalInfo   
	 * @param boo   是办理还是回退    true（办理）  false（回退）
	 */
	public void disposeOrRollback(String id,String approvalInfo,boolean boo,String userName);
	
	/**
	 * 已归档申请列表
	 * @param pageBean
	 * jrsj < new Date <cqsj 
	 * jmsj   和  lksj 是实际的进出门登记时刷新的时间  是实际时间
	 * @return
	 */
	public PageBean getInAndOutApplyArchived(PageBean pageBean);
	
	/**
	 * 当日进出门登记列表
	 * @param pageBean
	  *  此为当日的进出门登记列表，其中有已登记和已出门状态的列表数据
	 * @return
	 * @throws ParseException
	 */
	public PageBean getInAndOutRegisterList(PageBean pageBean)throws ParseException;
	
	/**
	 * 获取进出门详情
	 * @param id
	 * @return
	 */
	public Map<String, Object> getJcmxxById(String id);
	
	/**
	 * 生成进门登记
	 * @param object  进入AB门实体
	 * @param ids		外来人员业务id（数组）
	 */
	public Map<String, Object> inDoorRegister(String id,List<WxryInfoVo> list1,List<ToolsToForeignAidstaffVo> list2,WxrdjEntityVo object);
	
	/**
	 * 生成出门登记
	 * @param id  出AB门主键id，多个以逗号连接
	 * @param wlrdjAB
	 */
	public void outDoorRegister(String id,List<WxryInfoVo>list1,List<ToolsToForeignAidstaffVo> list2, WxrdjEntityVo wxrdj);
	
	/**
	 * 获取证件类型和name
	 * @param zjlxId
	 * @return
	 */
	List<Map<String, Object>> getZjlxAndName();
	
	/**
	 * 获取民族类型
	 * @param zjlxId
	 * @return
	 */
	List<Map<String, Object>> getMz();
	
	/**
	 * 获取文化程度类型
	 * @param zjlxId
	 * @return
	 */
	List<Map<String, Object>> getWhcd();
	
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
