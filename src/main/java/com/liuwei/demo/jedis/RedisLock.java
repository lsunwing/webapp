package com.liuwei.demo.jedis;

public interface RedisLock {

    public String getLock(String lockName, int timeout, int expire);

    public boolean releaseLock(String lockName, String value);
}
