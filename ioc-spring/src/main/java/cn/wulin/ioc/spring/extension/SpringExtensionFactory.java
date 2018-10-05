package cn.wulin.ioc.spring.extension;

import java.util.Set;
import org.springframework.context.ApplicationContext;
import cn.wulin.ioc.extension.ExtensionFactory;
import cn.wulin.ioc.util.ConcurrentHashSet;

/**
 * SpringExtensionFactory
 *
 * @author william.liangf
 */
public class SpringExtensionFactory implements ExtensionFactory {

    private static final Set<ApplicationContext> contexts = new ConcurrentHashSet<ApplicationContext>();

    public static void addApplicationContext(ApplicationContext context) {
        contexts.add(context);
    }

    public static void removeApplicationContext(ApplicationContext context) {
        contexts.remove(context);
    }

    @SuppressWarnings("unchecked")
    public <T> T getExtension(Class<T> type, String name) {
        for (ApplicationContext context : contexts) {
            if (context.containsBean(name)) {
                Object bean = context.getBean(name);
                if (type.isInstance(bean)) {
                    return (T) bean;
                }
            }
        }
        return null;
    }

}
