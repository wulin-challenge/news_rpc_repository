package cn.wulin.ioc.test1.defaultImpl.test;

import org.junit.Test;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;
import cn.wulin.ioc.test1.defaultImpl.interfase.FileSuffix;

/**
 * 测试当子类不存在就用父亲实现
 * @author wubo
 */
public class TestFileSuffix {
	
	@Test
	public void testText(){
		String protocol = "txt1";
		String parentProtocol = "text_abstract";
		
		FileSuffix fileSuffix = InterfaceExtensionLoader.getExtensionLoader(FileSuffix.class).getAdaptiveExtension();
		String html = fileSuffix.getHtml(getURL(protocol,parentProtocol));
		System.out.println(html);
		
	}
	
	private URL getURL(String protocol,String parentProtocol){
		boolean hasExtension = InterfaceExtensionLoader.getExtensionLoader(FileSuffix.class).hasExtension(protocol);
		if(!hasExtension){
			protocol = null;
		}
		URL url = new URL(protocol, "0.0.0.0", 0);
		url = url.addParameter("parent_protocol", parentProtocol);
		return url;
	}

}
