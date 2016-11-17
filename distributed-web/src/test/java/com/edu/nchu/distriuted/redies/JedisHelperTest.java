//package com.edu.nchu.distriuted.redies;
//
//import com.edu.nchu.distriuted.DistributedWebApplication;
//import com.edu.nchu.distriuted.redis.JedisHelper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// * Created by Alen on 2016/11/13.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = DistributedWebApplication.class)
//public class JedisHelperTest {
//
//    @Autowired
//    JedisHelper jedisHelper;
//
//    @Test
//    public void testSet(){
//        try {
//            jedisHelper.set("test", "123456", 600000);
//            System.out.println("setting key [test] as value [123456] with one hour effective");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGet(){
//        String key = "test";
//        try {
//            String value = jedisHelper.get(key);
//            System.out.println("get redis key ["+key+"] value ["+value+"]");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
