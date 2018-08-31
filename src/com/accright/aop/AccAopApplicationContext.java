package com.accright.aop;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于创建代理类
 */
public class AccAopApplicationContext {
    /**
     * 存放代理类的集合
     */
    public static ConcurrentHashMap<String,Object> proxyBeanMap = new ConcurrentHashMap<String, Object>();
    static {
        initAopBeanMap("com.accright.aop");
    }

    /**
     * 初始化容器
     * @param basePath
     */
    public static void initAopBeanMap(String basePath){
        try{
            Set<Class<?>> classSet = ClassUtil.getClassSet(basePath);
            for(Class clazz : classSet){
                if(clazz.isAnnotationPresent(AccAspect.class)){
                    Method[] methods = clazz.getMethods();
                    for(Method method : methods){
                        if(method.isAnnotationPresent(AccPointCut.class)){
                            //找到切点
                            AccPointCut pointCut = (AccPointCut)method.getAnnotations()[0];
                            String pointCutStr = pointCut.value();
                            //System.out.println("pointCutStr:" + pointCutStr);
                            String[] pointCutArr = pointCutStr.split("_");
                            //被代理的类名
                            String className = pointCutArr[0];
                            //System.out.println("className:" + className);
                            //被代理的方法名
                            String methodName = pointCutArr[1];
                            // System.out.println("methodName:" + methodName);

                            //根据切点 创建被代理对象
                            Object targeObj = ReflectionUtil.newInstance(className);
                            //根据切面类创建代理者
                            AbstractMethod proxyer = (AbstractMethod)ReflectionUtil.newInstance(clazz);
                            //设置代理的方法
                            proxyer.setProxyMethodName(methodName);

                            Object object = proxyer.creatProxyObject(targeObj);

                            if(object != null){
                                proxyBeanMap.put(targeObj.getClass().getSimpleName().toLowerCase(),object);
                            }
                        }
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
