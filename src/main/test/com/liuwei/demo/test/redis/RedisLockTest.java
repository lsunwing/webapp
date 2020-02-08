package com.liuwei.demo.test.redis;

import com.liuwei.demo.jedis.JedisClient;
import com.liuwei.demo.jedis.RedisLock;
import com.liuwei.demo.jedis.RedisLockImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

public class RedisLockTest implements Runnable {

    private String lockName = "LWLock_12_22";

    private static final int count = 500; // 500个线程

    private GoodRepository gr = new GoodRepository();

    private static CountDownLatch countDownLatch = new CountDownLatch(count); // 发令枪

    static RedisLock redisLock = null;


    public static void main(String[] args) {
        //初始化一个spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:config/spring/applicationContext-redis.xml");

        // 从容器中获得JedisClient对象,(拿到接口的对象)
        redisLock = applicationContext.getBean(RedisLock.class);

        // 500个客户同时抢购20个商品
        for (int i=0; i<count; i++) {
            new Thread(new RedisLockTest()).start();
            countDownLatch.countDown();
        }
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
        }
        catch (Exception e) {

        }

        // 同时跑
        String lockValue = redisLock.getLock(lockName, 3000, 3000);
        if (lockValue != null) {
            // 获取redis分布式锁成功
            gr.buy();
        }
        redisLock.releaseLock(lockName, lockValue);

    }
}

