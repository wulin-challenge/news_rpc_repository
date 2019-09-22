package com.bjhy.news.demo.api;

import java.util.Date;
import java.util.Map;

import com.bjhy.platform.commons.pager.PageBean;

/****
 * 狱政系统对教育改造提供api
 * @author yedai
 *
 */
public interface EducationRemoService {
	

	/***
	 * 查询罪犯上月考核分
	 * @param zfid 罪犯id
	 * @return
	 * @throws Exception
	 */
	public  Map<String, Object> findUpMonthPoint(String zfid) throws Exception;

   /****
    * 日记载情况
    * @param zfid 罪犯id
    * @return
    * @throws Exception
    */
	public  PageBean findCurrentMonthPoint(String zfid) throws Exception;




}
