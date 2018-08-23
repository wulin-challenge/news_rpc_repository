package com.bjhy.news.common.notify;

import java.util.Set;

import com.bjhy.cache.toolkit.util.LoggerUtils;
import com.bjhy.news.common.exception.NewsRpcException;
import com.bjhy.news.common.util.NewsConstants;

import cn.wulin.ioc.URL;

public abstract class AbstractNotifyListener implements NotifyListener{

	@Override
	public void notify(URL url){
		try {
			//只执行包含有相同类别的通知
			String category = url.getParameter(NewsConstants.CATEGORY_KEY).trim();
			if(getCategory().contains(category)){
				doNotify(url);
			}
		} catch (NewsRpcException e) {
			//所有异常必须转换为 NewsRpcException统一在此处处理
			LoggerUtils.warn(e.getMessage(), e);
		}
	}
	
	protected abstract void doNotify(URL url) throws NewsRpcException;
	
	/**
	 * 得到实现类的category
	 * @return
	 */
	protected abstract Set<String> getCategory();
}
