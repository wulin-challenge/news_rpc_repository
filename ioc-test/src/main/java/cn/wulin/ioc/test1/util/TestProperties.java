package cn.wulin.ioc.test1.util;

import java.util.Properties;

import cn.wulin.ioc.util.ConfigUtils;

public class TestProperties {
	
	public static void main(String[] args) {
		String property2 = System.getProperty("brace.id");
		Properties properties = System.getProperties();
		properties.get("brace.id");
		String property = ConfigUtils.getProperty("brace.id", "1");
		String property3 = ConfigUtils.getProperty("brace.name", "ll");
		System.out.println(property);
		System.out.println(property3);
	}

}
