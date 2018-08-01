package com.bjhy.news.common.util;
import cn.wulin.brace.core.utils.SerializeUtil;

/**
 * 序列化工具类（基于 Protostuff 实现）
 *
 * @author wubo
 */
public class SerializationUtil {

    private SerializationUtil() {
    }

    /**
     * 序列化（对象 -> 字节数组）
     */
    public static <T> byte[] serialize(T obj) {
    	return SerializeUtil.serialize(obj);
    }

    /**
     * 反序列化（字节数组 -> 对象）
     */
    public static <T> T deserialize(byte[] data, Class<T> cls) {
       return SerializeUtil.deserialize(data, cls);
    }

}
