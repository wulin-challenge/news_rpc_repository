package cn.wulin.ioc.test1.defaultImpl.impl;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.test1.defaultImpl.base.TextAbstractFileSuffix;

public class TxtText extends TextAbstractFileSuffix{
	private String txt = "txt_";

	@Override
	public String getHtml(URL url) {
		return txt+"html";
	}

	@Override
	public String getPdf(URL url) {
		return txt+"pdf";
	}

	@Override
	public String getWord(URL url) {
		return txt+"word";
	}
}
