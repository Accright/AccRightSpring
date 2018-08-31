package com.accright.ioc;

import org.apache.commons.beanutils.ConvertUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * 解析XML类 首先加载
 */
public class AccClassPathXmlApplicationContext {
    private List<BeanJava> beanList = new ArrayList<BeanJava>();//存储所有的beanlist
    private Map<String,Object> beanMap = new HashMap<String,Object>();//实例化之后的bean存入map 根据id进行获取
    /**
     * 构造函数 用于解析初始化Spring
     */
    public AccClassPathXmlApplicationContext(String filePath){
        this.readXml(filePath);
        this.initBean();
        this.ioc();
    }

    /**
     * 读取XML文件   将其加载入内存
     */
    private void readXml(String filePath){
        //引入dom4j解析xml
        SAXReader saxReader = new SAXReader();//强引用
        Document document = null;

        try{
            URL xmlPath = this.getClass().getClassLoader().getResource(filePath);//获取配置文件的路径
            document = saxReader.read(xmlPath);//使用dom4j解析xml

            //解析开始...
            Element rootElement = document.getRootElement();
            Iterator i = rootElement.elementIterator();
            while(i.hasNext()){
                Element ele = (Element) i.next();
                String id = ele.attributeValue("id");
                String className = ele.attributeValue("className");
                List<Properties> prolist = new ArrayList<Properties>();
                BeanJava bean = new BeanJava(id,className);
                //解析关联的依赖注入
                Iterator j = ele.elementIterator();
                while(j.hasNext()){
                    Element proEle = (Element)j.next();
                    String name = proEle.attributeValue("name");
                    String ref = proEle.attributeValue("ref");
                    String val = proEle.attributeValue("val");
                    Properties properties = new Properties(name,ref,val);
                    prolist.add(properties);
                }
                bean.setRefProperties(prolist);
                this.beanList.add(bean);//加载进内存
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据id和className示例化bean
     */
    private void initBean(){
        if(beanList != null && beanList.size() > 0){
            for(BeanJava bean : beanList){
                //使用反射机制进行实例化
                try{
                    if(bean.getClassName() != null && !bean.getClassName().equals("") && bean.getId() != null && !bean.getId().equals("")){
                        Object beanObject = Class.forName(bean.getClassName()).newInstance();
                        beanMap.put(bean.getId(),beanObject);
                        System.out.println("Bean实例化成功,id为"+bean.getId());
                    }else{
                        System.out.println("实例化出现错误 id或className为空");
                    }
                }catch (Exception e){
                    System.out.println("实例化出现错误");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 实现依赖注入
     */
    private void ioc(){
        if(beanList != null && beanList.size() > 0){
            for (BeanJava javaBean : beanList){
                Object bean = beanMap.get(javaBean.getId());//获取bean
                if(bean != null){
                    try{
                        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());//获取实例化的bean类信息
                        PropertyDescriptor[] propertyDescriptorList = beanInfo.getPropertyDescriptors();//获取实例化类的属性描述
                        //开始解析依赖注入
                        List<Properties> proList = javaBean.getRefProperties();
                        if(proList != null && proList.size() > 0){
                            //循环所有的依赖
                            for (Properties properties : proList){
                                for (PropertyDescriptor propertyDescriptor : propertyDescriptorList){
                                    //如果用户注入的beanname和spring托管的beanname是相同的  使用反射机制进行bean的依赖注入
                                    if(properties.getName().equals(propertyDescriptor.getName())){
                                        Method setter = propertyDescriptor.getWriteMethod();//获取Spring托管的Bean的setter方法
                                        if(setter != null){
                                            Object value = null;//依赖注入的值
                                            //如果依赖注入的ref不为空  注入的为Spring托管对象
                                            if(properties.getRef() != null && !properties.getRef().equals("")){
                                                value = beanMap.get(properties.getName());
                                            }else{
                                                //否则转化为依赖注入的字段需要的类型
                                                value = ConvertUtils.convert(properties.getVal(),propertyDescriptor.getPropertyType());
                                            }
                                            setter.setAccessible(true);//反射机制可以绕过private和protected
                                            try{
                                                //使用反射进行依赖注入
                                                setter.invoke(bean,value);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                        }
                                        break;//跳出依赖循环  查找下一个依赖
                                    }
                                }
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 根据id获取实例化后的Bean
     */
    public Object getBean(String beanid){
        return beanMap.get(beanid);
    }
}
