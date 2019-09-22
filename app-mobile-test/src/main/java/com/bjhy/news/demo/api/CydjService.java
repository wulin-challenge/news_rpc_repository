package com.bjhy.news.demo.api;

import java.util.List;
import java.util.Map;

import com.bjhy.news.demo.vo.CydjdhAllocationEntityVo;


/**
 * 处遇等级(对外对网超提供接口)
 * 
 *
 */
public interface CydjService {
	
	/****
	 * 根据罪犯bh查询积分
	 * @param zfbh
	 * @return
	 * @throws Exception
	 */
	Map<String, String>  getJf(String zfbh) throws Exception;
	
	/***
	 * 罪犯激励处遇兑换次数查询接口）
	 * @param zfbh
	 * @return
	 * @throws Exception
	 */
	List<CydjdhAllocationEntityVo>  getJlcydh(String zfbh) throws Exception;
	
	/*****
	 * 网超激励处遇兑换申请同步接口（网超调用）
	 * 参数：罪犯编号  激励处遇兑换数据vo对象
	 */
	Map<String, String>  dealJlcy(String zfbh,Object object) throws Exception;
	/****
	 * 激励处遇待遇查询接口（网超调用）
	 * 根据处遇等级code查询
	 */
	Map<String, Object>  getBaseCydy(String codeId) throws Exception;
	
	/****
	 * 兑换激励处遇商品
	 * @param zhbh
	 * @param id
	 * @param amount
	 * @return
	 */
	String dealJlcy(String zhbh,String id,int amount) throws Exception;
	
	/****
	 * 查询商品
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CydjdhAllocationEntityVo  getJlcydhById(String id) throws Exception;
	
	
	/****
	 * 根据业务id查询该数据是否已归档
	 * @param ywid
	 * @return
	 * @throws Exception
	 */
	Map<String, String> checkIsArchive(String ywid) throws Exception;
	
}
