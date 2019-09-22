package com.bjhy.news.demo.api;

/**
 * 刑法执行需要的生卫提供消费记录合计出账金额的接口
 * @author wwl
 *
 */
public interface SwToXfzxService {
	/**
	 * 根据罪犯档案号获取罪犯的支出记录字符串
	 * @param dah 罪犯档案号
	 * @param startDate 时间段起始
	 * @param endDate 时间段截止
	 * @return 支出记录字符串
	 * @throws Exception
	 */
	public String getAb02Byzhbh(String dah,String startDate,String endDate)throws Exception;
}
