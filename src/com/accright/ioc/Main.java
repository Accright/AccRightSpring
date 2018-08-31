package com.accright.ioc;

import com.accright.aop.AccAopApplicationContext;
import com.accright.aop.Test;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        AccAopApplicationContext accAopApplicationContext = new AccAopApplicationContext();
        ConcurrentHashMap<String,Object> concurrentHashMap = accAopApplicationContext.proxyBeanMap;
        Test test = (Test) concurrentHashMap.get("test");
        test.testAcpect();
        test.notestAcpect();
    }
}
