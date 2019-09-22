package com.bjhy.news.demo.api;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import com.bjhy.news.demo.vo.ZfRoomCodeVo;
import com.bjhy.platform.commons.pager.PageBean;

/**
 * 作为3.0和生卫的中间层，接收参数和返回结果
 * 生卫床铺 app功能的 中间层
 * @author cxh
 *
 */
public interface SwcpglAPPService {
	public void test();
	
	/**
	 * 监舍卫生管理   首页，得到左边的栋数和单元数
	 * @return
	 */
	public List<Map<String, Object>> getLdhsAndDyhs();
	
	/**
	 * 监舍卫生管理   首页，得到右边的 楼层数
	 * 入参：楼栋号，单元数   这两个其实都是左边接收的栋数和单元数，再传过来一次就行了
	 * @return
	 */
	public List<ZfRoomCodeVo> getAllFjhsNotDate(String ldh,String dys);
	
	/**
	 * 监舍卫生管理   首页，得到右边的 房间号
	 * 入参：楼栋号，单元数 ，层数   这三个其实都是左边接收的栋数和单元数，再加上选择的层数，再传过来一次就行了
	 * @return
	 */
	public List<ZfRoomCodeVo> getAllFjhsNotDate(String ldh,String dys,String cs);
	
	//-----------------------------------分割线 条款信息-------------------------------
	/**
	 * 通过条款类型id获取条款信息（推荐使用条款类型id获取）
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getClauseInfoById(String id);
	
	/**
	 * 通过条款名称获取条款信息
	 * @param name
	 * @return
	 */
	List<Map<String, Object>> getClauseInfoByName(String name);
	
	/**
	 * 通过条款id获取扣分标准和扣分范围
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getKfInfo(String id);
	
	/**
	 * 获取检查类型
	 * @return
	 */
	List<Map<String, Object>> findJclx();
	
	//-----------------------------------分割线 监舍卫生信息-------------------------------
	/**
	 * 通过房间id获取该房间的历史纪录
	 * @param roomId
	 * @return
	 */
	PageBean getHistoryInfoByRoomId(String roomId,PageBean pageBean);
	
	/**
	 * 通过检查记录的id获取检查记录的具体信息
	 * @param checkId
	 * @return
	 */
	List<Map<String, Object>> getHistoryDetailsByCheckId(String checkId);
	
	/**
	 * 保存房间的卫生检查情况
	 * @param roomId
	 * @return
	 */
	String saveHygieneCheck(Map<String, Object> map);
	
	/**
	 * 通过图片id获取图片
	 * @param picId
	 * @return
	 */
	byte[] getFileInputStreamByCheckId(String picId);
	
}
