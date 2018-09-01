# 简介
AccRightSpring是根据Spring原理，使用Java的反射机制自己实现的Spring IOC框架，可以根据Bean.xml中的配置将所有的Bean信息依托框架管理，并进行依赖注入。
代码中有详细的注释，可以快速的理解Spring框架的IOC原理。
后续根据SpringAOP的原理，简单实现了Spring的AOP框架，可以根据在类上使用@AccAspect注解并在方法上使用@AccPointcut切点注解实现前置增强(advice)和后置增强(advice)，依赖于cglib动态代理。
该框架适合基础的Spring原理的理解。
### 依赖

| 模块                   | 释义                          |
| ---------------------- | ----------------------------- |
| dom4j                  | 实现xml文件的解析             |
| commons-beanutils-core | 将依赖注入的类型进行转化      |
| commons-logging        | commons-beanutils-core的依赖  |
| cglib-2.2              | cglib-2.2动态代理的依赖       |
| asm-3.2                | cglib-2.2依赖的字节码生成包   |

### 其他
- [我的博客](https://www.accright.com)

### 开源协议
 [MIT](https://gitee.com/yadong.zhang/DBlog/blob/master/LICENSE)



