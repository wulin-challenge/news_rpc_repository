/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.wulin.ioc.test1.extensionloader.ext3;

import cn.wulin.ioc.URL;
import cn.wulin.ioc.extension.Adaptive;
import cn.wulin.ioc.extension.SPI;

/**
 * @author ding.lid
 */
@SPI("impl1")
public interface UseProtocolKeyExt {
    // protocol key在第二个
    @Adaptive({"key1", "protocol"})
    String echo(URL url, String s);

    // protocol key在第一个
    @Adaptive({"protocol", "key2"})
    String yell(URL url, String s);
    
    // 只有一个protocol
    @Adaptive({"protocol"})
    String onlyProtocol(URL url, String s);
}