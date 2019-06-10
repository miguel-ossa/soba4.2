package com.perfmath.spring.soba.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.perfmath.spring.soba.util.PerfBasicUtil;

public class PerfBasicLoggingHandler implements InvocationHandler {
    private Object target;
    public static Object createProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new PerfBasicLoggingHandler(target));
    }

    public PerfBasicLoggingHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
    	long startTime = System.currentTimeMillis();
        Object result = method.invoke(target, args);       
        PerfBasicUtil.log(target.toString() + "@" + method.getName() + 
        		":" + (System.currentTimeMillis() - startTime));        		        
        return result;

    }
}
