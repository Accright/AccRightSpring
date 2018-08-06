/**
 * 用来进行实例化测试
 */
public class Student {
    private String id;//
    private String name;//
    private String sex;//

    private StudentBase studentBase;//依赖注入的类

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

    public StudentBase getStudentBase() {
        return studentBase;
    }

    public void setStudentBase(StudentBase studentBase) {
        this.studentBase = studentBase;
    }

    public void printStudent(){
        System.out.println("学生id为"+this.id+"学生名字为"+this.name+"学生性别为"+this.sex);
        studentBase.printStudent();
    }
}
