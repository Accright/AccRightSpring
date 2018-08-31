package com.accright.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射机制的类集合  通过反射创建对象 设置成员变量及执行方法
 */
public class ReflectionUtil {
    /**
     * 创建实例
     */
    public static Object newInstance(Class<?> cls){
        Object instance;

        try{
            instance = cls.newInstance();
        }catch (Exception e){
            throw new RuntimeException();
        }
        return instance;
    }

    /**
     * 创建实例 根据类名
     */
    public static Object newInstance(String className){
        Class<?> cls = ClassUtil.loadClass(className);
        return newInstance(cls);
    }

    /**
     * 调用方法
     */
    public static Object invokeMethod(Object obj, Method method, Object... args){
        Object result;
        try{
            method.setAccessible(true);
            result = method.invoke(obj,args);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     */
    public static void setField(Object obj, Field field, Object value){
        try{
            field.setAccessible(true);
            field.set(obj,value);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
