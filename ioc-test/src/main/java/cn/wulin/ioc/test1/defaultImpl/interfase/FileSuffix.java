package cn.wulin.ioc.test1.defaultImpl.interfase;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.Adaptive;
import cn.wulin.ioc.extension.SPI;

@SPI
public interface FileSuffix {
	
	@Adaptive({"protocol","parent_protocol"})
	String getHtml(URL url);
	
	@Adaptive({"protocol","parent_protocol"})
	String getPdf(URL url);
	
	@Adaptive({"protocol","parent_protocol"})
	String getWord(URL url);

}
