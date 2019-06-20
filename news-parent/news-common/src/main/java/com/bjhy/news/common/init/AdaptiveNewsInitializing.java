package com.bjhy.news.common.init;

import com.bjhy.news.common.connect.NewsConnect;

import cn.wulin.ioc.extension.Adaptive;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;

@Adaptive
public class AdaptiveNewsInitializing implements NewsInitializing{

	private static volatile String DEFAULT_NEWS_INITIALIZING;

	public static void setDefaultAdaptiveNewsInitializing(String newsInitializing) {
		DEFAULT_NEWS_INITIALIZING = newsInitializing;
	}

	/**
	 * 得到真正的消息rpc连接
	 * @return
	 */
	private NewsInitializing getNewsInitializing() {
		NewsInitializing newsInitializing;
		InterfaceExtensionLoader<NewsInitializing> loader = InterfaceExtensionLoader.getExtensionLoader(NewsInitializing.class);
		String name = DEFAULT_NEWS_INITIALIZING; // copy reference
		if (name != null && name.length() > 0) {
			newsInitializing = loader.getExtension(name);
		} else {
			newsInitializing = loader.getDefaultExtension();
		}
		return newsInitializing;
	}

	@Override
	public void initBefore(NewsConnect newsConnect) {
		NewsInitializing newsInitializing = getNewsInitializing();
		if(newsInitializing == null) {
			return;
		}
		newsInitializing.initBefore(newsConnect);
	}

	@Override
	public void initAfter(NewsConnect newsConnect) {
		NewsInitializing newsInitializing = getNewsInitializing();
		if(newsInitializing == null) {
			return;
		}
		newsInitializing.initAfter(newsConnect);
	}

}
