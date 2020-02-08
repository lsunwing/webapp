package com.liuwei.demo.test.bean;

import com.liuwei.demo.bean.MyBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifecycleTest {

    @Test
    public void blTest() {
        //初始化一个spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:config/spring/applicationContext-lifecycle.xml");

        //从容器中获得JedisClient对象,(拿到接口的对象)
        MyBean myBean = (MyBean) applicationContext.getBean("myBean");
        System.out.println(myBean.getName());
        myBean.run();
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

}
