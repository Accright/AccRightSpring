package com.accright.ioc;

public class MainTest {
    //测试入口
    public static void main(String[] args){
        AccClassPathXmlApplicationContext accContext = new AccClassPathXmlApplicationContext("resource/bean.xml");
        Student student = (Student) accContext.getBean("student");
        student.printStudent();
    }
}
