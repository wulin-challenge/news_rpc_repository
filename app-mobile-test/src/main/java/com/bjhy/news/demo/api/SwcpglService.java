//package com.bjhy.news.demo.api;
//
//import java.util.List;
//
///**
// * 
// *  提供给生卫系统获取床铺管理里边的楼栋，监舍等信息的接口
// * @author cxh
// *
// */
//		
//public interface SwcpglService {
//	
//	/**
//	 * 根据生卫系统传过来的监狱name获取监区name
//	 * @param jyName
//	 * @return
//	 * @throws Exception
//	 */
//	public List<String> getJqNameByJyName(String jyName)throws Exception;
//	
//	/**
//	 * 通过监区name获取楼栋号
//	 * @param jqName
//	 * @return
//	 * @throws Exception
//	 */
//	public List<String> getLdhByJqName(String jqName)throws Exception;
//	
//	/**
//	 * 通过监区名字和楼栋号获取单元号
//	 * @param jqName
//	 * @return
//	 * @throws Exception
//	 */
//	public List<String> getDyhByNames(String jqName,String ldh)throws Exception;
//	
//	/**
//	 * 通过监区、楼栋号和单元号获取楼层号
//	 * @param jqName
//	 * @return
//	 * @throws Exception
//	 */
//	public List<String> getLchByNames(String jqName,String ldh,String dyh)throws Exception;
//	
//	/**
//	 * 通过监区、楼栋号、单元号和楼层号获取房间号
//	 * @param jqName
//	 * @return
//	 * @throws Exception
//	 */
//	public List<String> getFjhByNames(String jqName,String ldh,String dyh,String lch)throws Exception;
//}
