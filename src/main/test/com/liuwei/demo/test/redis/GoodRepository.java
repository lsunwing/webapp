package com.liuwei.demo.test.redis;

public class GoodRepository {
    private static volatile int goodCount = 20;

    public void buy() {
        if (goodCount > 0) {
            System.out.println("当前线程:" + Thread.currentThread().getName() +
                    "购买成功,仓库剩余" + (--goodCount));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("当前线程:" + Thread.currentThread().getName() +
                    "购买失败");
        }
    }
}
