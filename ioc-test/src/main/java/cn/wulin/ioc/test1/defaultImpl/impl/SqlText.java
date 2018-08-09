package cn.wulin.ioc.test1.defaultImpl.impl;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.test1.defaultImpl.base.TextAbstractFileSuffix;

public class SqlText extends TextAbstractFileSuffix {

	private String sql = "sql_";

	@Override
	public String getHtml(URL url) {
		return sql+"html";
	}

	@Override
	public String getPdf(URL url) {
		return sql+"pdf";
	}

	@Override
	public String getWord(URL url) {
		return sql+"word";
	}

}
