package cn.wulin.ioc.test1.defaultImpl.base;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.test1.defaultImpl.interfase.FileSuffix;

public abstract class AbstractFileSuffix implements FileSuffix{

	private String fileSuffix = "abstract_file_suffix";
	@Override
	public String getHtml(URL url) {
		return fileSuffix+"html";
	}

	@Override
	public String getPdf(URL url) {
		return fileSuffix+"pdf";
	}

	@Override
	public String getWord(URL url) {
		return fileSuffix+"word";
	}
}
