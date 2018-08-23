package com.bjhy.news.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bjhy.news.common.util.NewsConstants;

/**
 * 消息监听服务
 * 若调用方采用 sync(同步)调用,则被该注解标注的类的方法的远程调用方法
 * 若调用方采用asysn(异步)调用,则被该注解标注的类的方法都是mq消息机制的消费者处理方法
 * @author wubo
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface NewsListenerService {
	
	/**
     * 服务接口类
     */
    Class<?> value();
    
    /**
     * 服务版本号(仅仅适用于同步情况下调用)
     */
    String syncVersion() default "";
    
    /**
     * 服务处理超时时间(只适用于同步调用情况,异步调用该超时时间无效)
     * @return
     */
    int syncTimeout() default NewsConstants.DEFUALT_SYNC_TIMEOUT;
}
