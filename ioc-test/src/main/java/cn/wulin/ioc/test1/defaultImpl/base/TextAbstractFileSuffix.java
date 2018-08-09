package cn.wulin.ioc.test1.defaultImpl.base;

import cn.wulin.ioc.URL;

public class TextAbstractFileSuffix extends AbstractFileSuffix{
	
	String text = "text_abstract_";

	@Override
	public String getHtml(URL url) {
		return text+"html";
	}

	@Override
	public String getPdf(URL url) {
		return text+"pdf";
	}

	@Override
	public String getWord(URL url) {
		return text+"word";
	}
	

}
