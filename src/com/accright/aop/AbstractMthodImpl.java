package com.accright.aop;

@AccAspect
public class AbstractMthodImpl extends AbstractMethod{
    @AccPointCut("com.accright.aop.Test_testAcpect")
    public void testAspect(){

    }

    //前置方法增强
    @Override
    public void doBefore(){
        System.out.println(">>>>>>>>>>>>>前置增强启动 doBefore");
    }

    //后置方法增强
    @Override
    public void doAfter(){
        System.out.println(">>>>>>>>>>>>后置增强启动 doAfter");
    }
}
