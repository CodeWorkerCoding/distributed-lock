package com.edu.nchu.distriuted.redis;

/**
 * Created by fujianjian on 2016/11/13.
 */
public interface JedisHelper {

    String get(String key) throws Exception;

    void set(String key, String value, int cacheSeconds) throws Exception;

    long incr(String key) throws Exception;

    void expire(String key, int cacheSeconds) throws Exception;

    boolean tryLock(String key) throws Exception;

    void releaseLock(String key) throws Exception;
}
