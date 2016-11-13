package com.edu.nchu.distriuted.redis;

import org.apache.commons.codec.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * Created by fujianjian on 2016/11/13.
 */
@Service
public class JedisHelperImpl implements JedisHelper {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Override
    public String get(String key) throws Exception {
        RedisConnection connection = redisConnectionFactory.getConnection();
        byte[] keyByte = key.getBytes(CharEncoding.UTF_8);
        try {
            byte[] bytes = connection.get(keyByte);
            if (bytes == null){
                return null;
            }
            return new String(bytes, CharEncoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            logger.info("redis 获取数据出现情况，现发起一次重复连接", e);
            if (connection != null){
                connection.close();
            }
            connection = redisConnectionFactory.getConnection();
            byte[] bytes = connection.get(keyByte);
            if(bytes == null){
                return null;
            }
            return new String(bytes, CharEncoding.UTF_8);
        } finally {
            if (connection != null){
                connection.close();
            }
        }
    }

    /**
     * 设置redis键值对
     * @param key
     * @param value
     * @param cacheSeconds 如果为0，则永久不过期，否则n秒后过期
     * @throws Exception
     */
    @Override
    public void set(String key, String value, int cacheSeconds) throws Exception {
        RedisConnection connection = redisConnectionFactory.getConnection();
        byte[] keyByte = key.getBytes(CharEncoding.UTF_8);
        byte[] valueByte = value.getBytes(CharEncoding.UTF_8);
        try {
            connection.set(keyByte, valueByte);
            if (cacheSeconds != 0) {
                connection.expire(keyByte, cacheSeconds);
            }
        } catch (Exception e) {
            logger.warn("redis 保存数据出现情况，现发起一次重复连接", e);
            if (connection != null) {
                connection.close();
            }
            connection = redisConnectionFactory.getConnection();
            connection.set(keyByte, valueByte);
            if (cacheSeconds != 0) {
                connection.expire(keyByte, cacheSeconds);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    /**
     * 自增长+1
     * @param key
     * @return
     * @throws Exception
     */
    @Override
    public long incr(String key) throws Exception {
        RedisConnection connection = redisConnectionFactory.getConnection();
        byte[] keyByte = key.getBytes(CharEncoding.UTF_8);
        try {
            return connection.incr(keyByte);
        } catch (Exception e) {
            logger.warn("redis 获取数据出现情况，现发起一次重复连接", e);
            if (connection != null) {
                connection.close();
            }
            connection = redisConnectionFactory.getConnection();
            return connection.incr(keyByte);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * 设置过期时间
     * @param key
     * @return
     * @throws Exception
     */
    @Override
    public void expire(String key, int cacheSeconds) throws Exception {
        RedisConnection connection = redisConnectionFactory.getConnection();
        byte[] keyByte = key.getBytes(CharEncoding.UTF_8);
        try {
            if (cacheSeconds != 0) {
                connection.expire(keyByte, cacheSeconds);
            }
        } catch (Exception e) {
            logger.warn("redis 获取数据出现情况，现发起一次重复连接", e);
            if (connection != null) {
                connection.close();
            }
            connection = redisConnectionFactory.getConnection();
            if (cacheSeconds != 0) {
                connection.expire(keyByte, cacheSeconds);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public boolean tryLock(String key) throws Exception{
        long incr = incr(key);
        if(incr == 1){
            expire(key, 10);//10秒后自动释放锁，防止死锁
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void releaseLock(String key) throws Exception{
        set(key, "0", 0);
    }

}
