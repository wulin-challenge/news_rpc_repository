package com.bjhy.news.demo.api;

import java.util.Map;



/***
 * 狱政系统对刑罚执行提供api
 * @author yedai
 *
 */

public interface PunishmentService {
	
	/**
	 * 上传罪犯行政奖励审批表到服务器上
	 * @param zfid 
	 * @param kssj
	 * @param jssj
	 * @return 根据业务id存的文件标识
	 * @throws Exception
	 */
	Map<String, String>  getZfjlYwids(String zfid,String kssj,String jssj) throws Exception;
	
	/**
	 * 上传罪犯行政处罚审批表到服务器上
	 * @param zfid
	 * @param kssj
	 * @param jssj
	 * @return 根据业务id存的文件标识
	 * @throws Exception
	 */
	Map<String, String>  getZfcfYwids(String zfid,String kssj,String jssj) throws Exception;
	
	
	/*****
	 *  根据罪犯id获取上传到文件服务器中的罪犯的积极分子审批表的id
	 * @param zfid
	 * @param kssj
	 * @param jssj
	 * @return  根据业务id存的文件标识
	 * @throws Exception
	 */
	Map<String, String> getActivistsApprovalYwids(String zfid,String kssj,String jssj)throws Exception;
	
	
	/**
	 * 根据罪犯id获取上传到文件服务器中的罪犯的罪犯加扣分信息的id
	 * @param zfid
	 * @param kssj
	 * @param jssj
	 * @return 根据 zfid+"_"+kpi.getId() 作为标识
	 * @throws Exception
	 */
	Map<String, String> getKpiPointMainYwid(String zfid,String kssj,String jssj)throws Exception;
	
	
	/**
	 * 根据罪犯id获取上传到文件服务器中的罪犯的积分转表扬的id
	 * @param zfid
	 * @param kssj
	 * @param jssj
	 * @return 根据kpi.getId()+"_"+zfid 作为标识
	 * @throws Exception
	 */
	Map<String, String> getJftoBiaoyangYwid(String zfid,String kssj,String jssj)throws Exception;
	
	
	/**
	 * 根据罪犯id和时间段获取上传到文件服务器中罪犯的台账附件id
	 * @param zfid
	 * @param kssj 时间格式为2018
	 * @param jssj 时间格式为2018
	 * @return 根据zfid+"_"+year+"_tz"作为标识
	 * @throws Exception
	 */
	Map<String, String> getJftz(String zfid,String kssj,String jssj)throws Exception;
	

	/**
	 *  根据罪犯id获取文件服务器中考核记载表附件的id   
	 * @param zfid
	 * @param kssj 时间格式为字符串 2018-01
	 * @param jssj 时间格式为字符串 2018-01
	 * @return 	根据zfid+"_"+str+"_RJZ"作为标识
	 * @throws Exception
	 */
	Map<String, String> getKpiJz(String zfid, String kssj, String jssj)throws Exception;
	
	/**
	 * 根据罪犯id获取文件服务器中罪犯入监登记表的id
	 * @param zfid
	 * @return 根据zfid作为标识
	 * @throws Exception
	 */
	Map<String, String> getRjdjb(String zfid)throws Exception;
	
	/**
	 * 根据罪犯id获取罪犯信息并更改相应的状态
	 * @param zfid
	 * @param dztcode
	 * @param dztname
	 * @param xztcode
	 * @param xztname
	 */
	void  updateCriminalZt(String zfid, String dztcode, String dztname,String xztcode,String xztname);
	
	/****
	 * 根据罪犯id以及文书类别获取罪犯三书一表附件信息id
	 * @param zfid
	 * @param wslbCode
	 * @return
	 * @throws Exception
	 */
	Map<String, String> getSsyb(String zfid,String wslbCode)throws Exception;
}
