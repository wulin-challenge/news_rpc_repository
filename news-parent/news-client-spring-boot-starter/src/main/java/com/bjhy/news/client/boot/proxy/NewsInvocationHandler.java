package com.bjhy.news.client.boot.proxy;

import java.lang.reflect.Method;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

/**
 * spring 的cglib的代理
 * @author wulin
 *
 */
public class NewsInvocationHandler implements InvocationHandler{
	
	private String beanName;
	
	private Class<?> beanClass;
	
	private BeanFactory beanFactory;
	
	public NewsInvocationHandler(String beanName,Class<?> beanClass, BeanFactory beanFactory) {
		super();
		this.beanName = beanName;
		this.beanClass = beanClass;
		this.beanFactory = beanFactory;
	}

	@Override
	public Object invoke(Object arg0, Method method, Object[] arg2) throws Throwable {
		Object bean = beanFactory.getBean(beanName);
		
		 // 获取反射调用所需的参数
        Class<?> serviceClass = beanClass;
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] parameters = arg2;
        // 使用 CGLib 执行反射调用
        FastClass serviceFastClass = FastClass.create(serviceClass);
        FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
        return serviceFastMethod.invoke(bean, parameters);
	}

}
