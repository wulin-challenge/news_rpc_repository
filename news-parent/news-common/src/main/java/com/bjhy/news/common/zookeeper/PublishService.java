package com.bjhy.news.common.zookeeper;

import java.util.List;

import com.bjhy.news.common.domain.PublishServiceInfo;

import cn.wulin.ioc.extension.SPI;

/**
 * 发布服务
 * @author wubo
 *
 */
@SPI(value="default.publish.service")
public interface PublishService {

	/**
	 * 得到所有要发布的服务
	 */
	List<PublishServiceInfo> getPublishServiceInfo();
}
