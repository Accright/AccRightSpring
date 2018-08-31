package com.accright.ioc;

/**
 * 该类作为依赖注入Student
 */
public class StudentBase {
    private String id;//
    private String name;//
    private String sex;//

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public void printStudent(){
        System.out.println("依赖注入执行成功！--------学生id为"+this.id+"学生名字为"+this.name+"学生性别为"+this.sex);
    }
}
