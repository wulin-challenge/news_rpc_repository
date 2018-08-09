package cn.wulin.ioc.test1.defaultImpl.impl;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.test1.defaultImpl.base.TextAbstractFileSuffix;

public class ClazzText extends TextAbstractFileSuffix {

	private String clazz = "clazz_";

	@Override
	public String getHtml(URL url) {
		return clazz+"html";
	}

	@Override
	public String getPdf(URL url) {
		return clazz+"pdf";
	}

	@Override
	public String getWord(URL url) {
		return clazz+"word";
	}

}
