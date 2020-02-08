package com.liuwei.demo.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 验证Spring Bean生命周期
 * 接口：
 * 初始化：InitializingBean
 * 销毁：DisposableBean
 */
public class MyBean implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean, BeanDefinitionRegistryPostProcessor {

    private String name;

    // 构造方法
    public MyBean() {
        System.out.println("1.init MyBean.");
    }

    public void setName(String name) {
        System.out.println("2.设置属性");
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    // 实现BeanNameAware接口，spring将bean的id传给setBeanName()方法
    @Override
    public void setBeanName(String id) {
        System.out.println("3.invoke setBeanName method, id=" + id);
    }

    // 实现了ApplicationContextAware接口
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("4.invoke setApplicationContext method");
    }

    // 实现InitializingBean，则执行afterPropertiesSet，执行属性设置之后的操作
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("5.invoke afterPropertiesSet method.");
    }


    // 自定义配置方法
    public void setup() {
        System.out.println("6：执行自己配置的初始化方法");
    }
    //第八步执行初始化之后执行的方法
    public void run() {
        System.out.println("7：执行自身的业务方法");
    }



    // 实现DisposableBean接口，spring将调用它的distroy()接口方法。
    // 同样的，如果bean使用了destroy-method属性声明了销毁方法，则该方法被调用
    @Override
    public void destroy() throws Exception {
        System.out.println("8.MyBean has been destroy.");
    }

    public void selfDestory() {
        System.out.println("9.执行自己配置的销毁方法");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("postProcessBeanFactory");
    }
}
