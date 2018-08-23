package com.bjhy.news.common.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时过期缓存
 * @author wubo
 */
public class TimedOverdueCache {
	
	private static Logger logger = LoggerFactory.getLogger(TimedOverdueCache.class);
	
	/**
	 * 过期时间默认5 分钟
	 */
//	private static Long expireTime = 1000*5*60L;
	/**
	 * 表示永久不过期
	 */
	private static Long expireTime =Long.MAX_VALUE;
	
	private static ConcurrentHashMap<String, SingleExpireCacheEntity> expireCache = new ConcurrentHashMap<String, SingleExpireCacheEntity>();
	
	/**
	 * 清除过期缓存
	 */
	private static final ScheduledThreadPoolExecutor clearExpireCacheExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
	
	static{
		excutorExpireScheduled();//类加载了就执行
	}
	
	/**
	 * 设置值
	 * @param key
	 * @param value
	 */
	public static void put(String key,Object value){
		synchronized (TimedOverdueCache.class) {
			SingleExpireCacheEntity singleExpireCacheEntity = new TimedOverdueCache().new SingleExpireCacheEntity();
			singleExpireCacheEntity.setCacheObject(value);
			singleExpireCacheEntity.setCreateTime(new Date());
			expireCache.put(key, singleExpireCacheEntity);
		}
	}
	
	/**
	 * 获取值
	 * @param key
	 * @return
	 */
	public static Object get(String key){
		synchronized (TimedOverdueCache.class) {
			SingleExpireCacheEntity singleExpireCacheEntity = expireCache.get(key);
			if(singleExpireCacheEntity == null){
				return null;
			}
			boolean expire = isExpire(singleExpireCacheEntity);
			if(expire){
				return null;
			}
			return singleExpireCacheEntity.getCacheObject();
		}
	}
	
	/**
	 * 获取值
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(String key,Class<T> clazz){
		return (T) get(key);
	}
	
	/**
	 * 清除指定key的缓存
	 * @param key
	 */
	public static void remove(String key){
		synchronized (TimedOverdueCache.class) {
			expireCache.remove(key);
		}
	}
	
	/**
	 * 执行过期清除定时器
	 */
	public static void excutorExpireScheduled() {
		clearExpireCacheExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					timedCleanExpireCache();
				} catch (Exception e) {
					logger.error("excutorExpireScheduled: ", e);
				}
			}
		}, 5, 30, TimeUnit.SECONDS);
	}
	
	/**
	 * 定时清除过期缓存
	 */
	private static void timedCleanExpireCache(){
		List<String> reallyExpireCache = new ArrayList<>();
		
		//收集哪些缓存是过期的
		Set<Entry<String, SingleExpireCacheEntity>> entrySet = expireCache.entrySet();
		for (Entry<String, SingleExpireCacheEntity> entry : entrySet) {
			boolean expire = isExpire(entry.getValue());
			if(expire){
				reallyExpireCache.add(entry.getKey());
			}
		}
		
		//清除过期缓存
		for (String key : reallyExpireCache) {
			expireCache.remove(key);
		}
	}
	
	/**
	 * 缓存是否过期
	 * @param singleExpireCacheEntity 单个过期缓存实体
	 * @return
	 */
	private static boolean isExpire(SingleExpireCacheEntity singleExpireCacheEntity){
		Long createTime = singleExpireCacheEntity.getCreateTime().getTime();
		Long currentTime = new Date().getTime();
		
		if((currentTime-createTime)>=expireTime){
			return true;
		}
		return false;
	}
	
	/**
	 * 单个过期缓存实体
	 * @author wubo
	 */
	public class SingleExpireCacheEntity{
		/**
		 * 缓存对象
		 */
		private Object cacheObject;
		
		/**
		 * 创建时间
		 */
		private Date createTime;

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Object getCacheObject() {
			return cacheObject;
		}

		public void setCacheObject(Object cacheObject) {
			this.cacheObject = cacheObject;
		}
	}
}
