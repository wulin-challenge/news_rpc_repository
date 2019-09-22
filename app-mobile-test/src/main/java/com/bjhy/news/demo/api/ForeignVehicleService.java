package com.bjhy.news.demo.api;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.bjhy.news.demo.vo.CldjEntityVo;
import com.bjhy.news.demo.vo.ForeignStaffApprovalEntityVo;
import com.bjhy.news.demo.vo.ForeignVehicleApprovalVo;
import com.bjhy.news.demo.vo.ToolsToForeignAidstaffVo;
import com.bjhy.news.demo.vo.ToolsToForeignVehicleVo;
import com.bjhy.news.demo.vo.ToolsToForeignstaffVo;
import com.bjhy.news.demo.vo.WlryInfoVo;
import com.bjhy.news.demo.vo.WxrdjEntityVo;
import com.bjhy.news.demo.vo.WxryInfoVo;
import com.bjhy.platform.commons.pager.PageBean;

public interface ForeignVehicleService {

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
	 * @return
	 */
	public String saveInAndOutApply(ForeignVehicleApprovalVo object,List<ToolsToForeignVehicleVo> list);
	
	/**
	 * 保存并送审
	 */
	public void submitInAndOutApply(ForeignVehicleApprovalVo object,List<ToolsToForeignVehicleVo> list);
	
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
	 * @return
	 */
	public PageBean getInAndOutApplyArchived(PageBean pageBean);
	
	/**
	 * 当日进出门登记列表
	 * @param pageBean
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
	 * 通过外来车辆申请id，获取工具信息
	 * @param id
	 */
	public List<ToolsToForeignVehicleVo> getGjinfoById(String id);
	
	/**
	 * 生成进门登记
	 * @param object  进入AB门实体
	 * @param ids		外来人员业务id（数组）
	 */
	public Map<String, Object> inDoorRegister(ForeignVehicleApprovalVo foreignVehicle,CldjEntityVo object,List<ToolsToForeignVehicleVo>list2);
	
	/**
	 * 生成出门登记
	 * @param id  出AB门主键id，多个以逗号连接
	 * @param wlrdjAB
	 */
	public void outDoorRegister(ForeignVehicleApprovalVo foreignVehicle, CldjEntityVo object,List<ToolsToForeignVehicleVo>list2);
	
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
