/**
 * 依赖注入相关的类
 */
public class Properties {
    private String name;//name
    private String ref;//关联类
    private String val;//值
    public Properties(String name,String ref,String val){
        this.name = name;
        this.ref = ref;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
