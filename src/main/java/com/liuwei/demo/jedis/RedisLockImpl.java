package com.liuwei.demo.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;

public class RedisLockImpl implements RedisLock {

    public JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

// spring注入必须要求不能有自定义的构造
//    public RedisLockImpl(JedisPool jedisPool) {
//        this.jedisPool = jedisPool;
//    }

    @Override
    public String getLock(String lockName, int timeout, int expire) {

        Jedis jedis = jedisPool.getResource();
        try {
            String uuid = UUID.randomUUID().toString();

            long endTime = System.currentTimeMillis() + timeout;
            while (System.currentTimeMillis() < endTime) {
                if (jedis.setnx(lockName, uuid) == 1) {
                    if (jedis.ttl(lockName) == -1) {
                        jedis.expire(lockName, expire);
                    }
                }
            }
        } catch (Exception e) {

        }
        finally {
            jedis.close();
        }

        return "";

    }

    @Override
    public boolean releaseLock(String lockName, String value) {
        Jedis jedis = jedisPool.getResource();

        try {
            while (jedis.exists(lockName)) {
                jedis.watch(lockName);

                if (value.equals(jedis.get(lockName))) {
                    Transaction transaction = jedis.multi();
                    transaction.del(lockName);
                    List<Object> execQueue = transaction.exec();
                    if (execQueue != null) {
                        return true;
                    }
                }
                break;
            }
        }
        catch (Exception e) {

        }
        finally {
            jedis.close();
        }
        return false;
    }
}
