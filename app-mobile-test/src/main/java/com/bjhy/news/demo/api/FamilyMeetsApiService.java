package com.bjhy.news.demo.api;

import java.util.List;
import java.util.Map;

import com.bjhy.news.demo.vo.KinshipMeetApplyVo;
import com.bjhy.news.demo.vo.OffenderKinshipVo;
import com.bjhy.news.demo.vo.OffenderVo;
import com.bjhy.platform.commons.pager.PageBean;

public interface FamilyMeetsApiService {
	
	/**
	 * 获取待办列表，支持搜索，搜索封装在PageBean的conditions中
	 * @param pageBean 查询参数为PageBean类型，包括currentPage和rowsPerPage
	 * @return 返回分页数据，封装在PageBean
	 */
	PageBean getApplyListNotBeenDone(PageBean pageBean, String userName);
	
	/**
	 * 获取已办列表，支持搜索，搜索封装在PageBean的conditions中
	 * @param pageBean 查询参数为PageBean类型，包括currentPage和rowsPerPage
	 * @return 返回分页数据，封装在PageBean
	 */
	PageBean getApplyListBeenDone(PageBean pageBean,String userName);
	
	/**
	 * 根据监区来获得该监区中的所有罪犯
	 * @param orgCodeList 监区的orgCode的List
	 * @return 返回罪犯列表，OffenderVo对象为罪犯对象
	 */
	PageBean getOffenderListByPrisons(PageBean pageBean,List<String> orgCodeList,String userName);
	
	/**
	 * 获取亲情会见种类列表
	 * @return 返回亲情会见种类的List，KinshipMeetTypeVo为亲情会见种类对象
	 */
	List<Map<String, Object>> getKinshipMeetTypeList();
	
	/**
	 * 通过罪犯的id获取该罪犯的亲属
	 * @param id 罪犯的id
	 * @return 返回亲属列表，OffenderKinshipVo为罪犯亲属对象
	 */
	PageBean getOffenderKinshipsById(PageBean pageBean,String id);
	
	/**
	 * 新增罪犯亲属，如果id没有值，则新增
	 * 更新罪犯亲属，如果id有值，则更新
	 * @param offenderKinship 罪犯亲属对象
	 * @return 返回新增的罪犯亲属的id
	 */
	void saveOrUpdateOffenderKinship(OffenderKinshipVo offenderKinship);
	
	/**
	 * 根据id查询罪犯亲属
	 * @param id 亲属id
	 * @return 返回罪犯亲属对象
	 */
	OffenderKinshipVo getOffenderKinshipById(String id);
	
	/**
	 * 新增亲情会见申请，如果id没有值，则新增
	 * 更新亲情会见申请，如果id有值，则更新
	 * @param kinshipMeetApply 亲情会见申请记录   hjrs是会见人id以 , 分隔
	 * @return
	 */
	void saveOrUpdateApply(KinshipMeetApplyVo kinshipMeetApply,String hjrs,String userName);
	
	/**
	 * 亲情关系类别
	 */
	List<Map<String, Object>> getKinshipMeetRealationTypeList();
	
	/**
	 * 送审，开启一个审批流程
	 * @param 申请id 亲情会见申请  hjrs是会见人id以 , 分隔
	 * @return 送审成功以后将送审的记录返回，并且返回的亲情会见申请对象KinshipMeetApplyVo中的id不能为空，如果是新增，请将生成的id值填充进去。
	 * 
	 */
	void sendApproval(KinshipMeetApplyVo kinshipMeetApply,String userName,String hjrs);
	
	/**
	 * 根据id获取亲情会见申请详情
	 * @param id 亲情会见id
	 * @return KinshipMeetApplyVo亲情会见申请对象
	 */
	Map<String, Object> getKinshipMeetApplyDetailById(String id);
	
	/**
	 * 办理，办理一个审批任务
	 * @param 申请id 亲情会见申请对象 approvalInfo 是审核（回退）意见， userName 登录用户名
	 */
	void dispose(String id, String approvalInfo,String userName);
	
	/**
	 * 回退，将审批流程回退
	 * @param 申请id   是回退意见   userName 登录用户名
	 */
	void rollback(String id, String approvalInfo,String userName);
	
	
	/**
	 * 获取证件类型和name
	 * @param zjlxId
	 * @return
	 */
	List<Map<String, Object>> getZjlxAndName();
	
	/**
	 * 删除罪犯亲属，参数是id
	 * @param offenderKinship 罪犯亲属对象
	 * @return 
	 */
	void deleteOffenderKinship(String id);
	
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
