package com.accright.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义切面的注解  运行时 描述类和接口
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value=ElementType.TYPE)
public @interface AccAspect {

}
