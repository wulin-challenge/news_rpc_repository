package com.bjhy.news.client.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * news rpc 的spring boot 自动配置
 * @author wulin
 *
 */
@Configuration
public class NewsBootAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public NewsBootConfigurer newsBootConfigurer() {
		return new NewsBootConfigurer();
	}
}
