package com.accright.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 实现方法拦截器  用于方法拦截 重写 intercept方法
 */
public abstract class AbstractMethod implements MethodInterceptor {

    private Object targetObject;//被代理的对象
    private String proxyMethodName;//被代理的方法名

    public Object creatProxyObject(Object targetObj){
        this.targetObject = targetObj;
        //使用cglib的方式生成代理类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObj.getClass());
        enhancer.setCallback(this);
        //enhancer.setClassLoader(targetObj.getClass().getClassLoader());
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //获取要切入的方法名
        String methodName = this.proxyMethodName;
        //如果要切入的方法名和方法的名字相同 执行切入逻辑
        if(method != null && !"".equals(methodName.trim()) && methodName.trim().equals(method.getName())){
            this.doBefore();
        }
        Object result = methodProxy.invokeSuper(o,objects);
        if(method != null && !"".equals(methodName.trim()) && methodName.trim().equals(method.getName())){
            this.doAfter();
        }
        return null;
    }

    //前置方法增强
    public abstract void doBefore();

    //后置方法增强
    public abstract void doAfter();

    public String getProxyMethodName() {
        return proxyMethodName;
    }

    public void setProxyMethodName(String proxyMethodName) {
        this.proxyMethodName = proxyMethodName;
    }
}
