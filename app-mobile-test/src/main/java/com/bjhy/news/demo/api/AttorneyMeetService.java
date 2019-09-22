package com.bjhy.news.demo.api;

import java.util.List;

import com.bjhy.news.demo.vo.AttorneyMeetApplyVo;
import com.bjhy.news.demo.vo.MeetPurposeVo;
import com.bjhy.news.demo.vo.OffenderAttorneyVo;
import com.bjhy.news.demo.vo.PapersTypeVo;
import com.bjhy.news.demo.vo.WasAgencyPersonRelationVo;
import com.bjhy.platform.commons.pager.PageBean;

/**
 * 律师会见
 * @author wlh
 *
 */
public interface AttorneyMeetService {

	/**
	 * 获取代办列表，支持搜索，搜索封装在PageBean的conditions中
	 * @param pageBean 查询参数为PageBean类型，包括currentPage和rowsPerPage
	 * @param username 用户名
	 * @return 返回分页数据，封装在PageBean
	 */
	PageBean getApplyListNotBeenDone(PageBean pageBean, String username);

	/**
	 * 获取已办列表，支持搜索，搜索封装在PageBean的conditions中
	 * @param pageBean 查询参数为PageBean类型，包括currentPage和rowsPerPage
	 * @return 返回分页数据，封装在PageBean
	 */
	PageBean getApplyListBeenDone(PageBean pageBean, String username);

	/**
	 * 获取会见事由列表
	 * @return MeetPurposeVo为会见事由对象
	 */
	List<MeetPurposeVo> getMeetPurposeList();

	/**
	 * 通过罪犯的id获取该罪犯的律师
	 * @param id 罪犯的id
	 * @return 返回律师列表，OffenderAttorneyVo为罪犯律师对象
	 */
	List<OffenderAttorneyVo> getOffenderAttorneysById(String id);

	/**
	 * 获取证件类型列表
	 * @return PapersTypeVo为证件类型对象
	 */
	List<PapersTypeVo> getPapersTypeList();

	/**
	 * 获取与被代理人关系列表
	 * @return 
	 */
	List<WasAgencyPersonRelationVo> getWasAgencyPersonRelationList();

	/**
	 * 新增一个律师。如果没有id，则新增
	 * @param offenderAttorney 罪犯律师对象
	 * @return 返回新增的律师的id
	 */
	String saveOffenderAttorney(OffenderAttorneyVo offenderAttorney);

	/**
	 * 更新律师，如果id有值，则更新
	 * @param offenderAttorney 罪犯律师对象
	 */
	void updateOffenderAttorney(OffenderAttorneyVo offenderAttorney);

	/**
	 * 通过id获取罪犯律师
	 * @param id 律师的id
	 * @return 返回律师对象
	 */
	OffenderAttorneyVo getOffenderAttorneyById(String id);

	/**
	 * 保存律师会见申请记录，如果没有id，就保存。
	 * @param attorneyMeetApply 律师会见申请对象
	 * @return 将申请的id返回
	 */
	String saveApply(AttorneyMeetApplyVo attorneyMeetApply);

	/**
	 * 更新律师会见申请记录，如果有id，就更新。
	 * @param attorneyMeetApply
	 */
	void updateApply(AttorneyMeetApplyVo attorneyMeetApply);

	/**
	 * 送审。开启审批流程
	 * @param attorneyMeetApply 律师会见申请
	 * @return
	 */
	AttorneyMeetApplyVo sendApproval(AttorneyMeetApplyVo attorneyMeetApply);

	/**
	 * 通过id获取律师会见申请详情
	 * @param id 申请id
	 * @return 律师会见申请记录
	 */
	AttorneyMeetApplyVo getAttorneyMeetApplyDetailById(String id);

	/**
	 * 
	 * @param attorneyMeetApply 
	 */
	/**
	 * 办理
	 * @param attorneyMeetApply 律师会见申请记录
	 * @param approvalInfo 审核意见信息
	 * @param username 用户名
	 */
	void dispose(AttorneyMeetApplyVo attorneyMeetApply, String approvalInfo, String username);

	/**
	 * 回退
	 * @param attorneyMeetApply 律师会见申请记录
	 */
	void rollback(AttorneyMeetApplyVo attorneyMeetApply, String approvalInfo, String username);

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
