import java.util.List;

/**
 * 管理的bean的实体类  加载进内存
 */
public class BeanJava {
    private String id;//Spring配置中的bean id
    private String className;//Spring 配置中的className
    private List<Properties> refProperties;//关联的依赖注入

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Properties> getRefProperties() {
        return refProperties;
    }

    public void setRefProperties(List<Properties> refProperties) {
        this.refProperties = refProperties;
    }

    /**
     * 构造函数
     */
    public BeanJava(String id, String className){
        this.id = id;
        this.className = className;
    }
}
