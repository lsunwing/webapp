package com.liuwei.demo.test;

import com.liuwei.demo.jedis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedisTest {

    @Test
    public void TestJedisClient() throws Exception {
        //初始化一个spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:config/spring/applicationContext-redis.xml");

        //从容器中获得JedisClient对象,(拿到接口的对象)
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        jedisClient.set("mytest", "jedisClient1");
        String string = jedisClient.get("mytest");
        System.out.println(string);
    }
}
