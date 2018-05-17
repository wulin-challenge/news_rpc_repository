package cn.wulin.ioc.test1.extensionloader.adaptive;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.Adaptive;
import cn.wulin.ioc.extension.SPI;

/**
 * @author ding.lid
 */
@SPI
public interface HasAdaptiveExt {
    @Adaptive
    String echo(URL url, String s);
}
