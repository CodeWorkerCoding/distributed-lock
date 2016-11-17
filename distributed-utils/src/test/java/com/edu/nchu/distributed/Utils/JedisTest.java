package com.edu.nchu.distributed.Utils;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.function.Predicate;

/**
 * 使用jedis操作redis数据库
 * Created by fujianjian on 2016/11/17.
 */
public class JedisTest {

    private Jedis jedis;

    @Before
    public void setup(){
        jedis = new Jedis("localhost", 6379);
    }

    /***
     * redies 存储字符串
     */
    @Test
    public void testSetString(){

        jedis.set("name", "fujianjian");
        System.out.println(jedis.get("name"));

        Long setnx = jedis.setnx("name", "shabi");
        System.out.println(setnx +"-"+ jedis.get("name"));

        jedis.append("name", " is a good man");
        System.out.println(jedis.get("name"));

        jedis.del("name");
        System.out.println(jedis.get("name"));

        jedis.mset("names","lili", "age", "18", "qq", "145256*****2");
        jedis.incr("age");
        System.out.println(jedis.get("names")+"-"+jedis.get("age")+"-"+jedis.get("qq"));
    }

    /**
     * redis操作Map
     */
    @Test
    public void testMap() {
        //-----添加数据----------
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user",map);
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);

        //删除map中的某个键值
        jedis.hdel("user","age");
        System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null
        System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2
        System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true
        System.out.println(jedis.hkeys("user"));//返回map对象中的所有key
        System.out.println(jedis.hvals("user"));//返回map对象中的所有value

        Iterator<String> iter=jedis.hkeys("user").iterator();
        while (iter.hasNext()){
            String key = iter.next();
            System.out.println(key+":"+jedis.hmget("user",key));
        }
    }

    /**
     * jedis操作List
     */
    @Test
    public void testList(){
        //开始前，先移除所有的内容
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework",0,-1));
        //先向key java framework中存放三条数据
        jedis.lpush("java framework","spring");
        jedis.lpush("java framework","struts");
        jedis.lpush("java framework","hibernate");
        //再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("java framework",0,-1));

        jedis.del("java framework");
        jedis.rpush("java framework","spring");
        jedis.rpush("java framework","struts");
        jedis.rpush("java framework","hibernate");
        System.out.println(jedis.lrange("java framework",0,-1));
    }

    /**
     * jedis操作Set
     */
    @Test
    public void testSet(){
        jedis.del("user");
        //添加
        jedis.sadd("user","liuling");
        jedis.sadd("user","xinxin");
        jedis.sadd("user","ling");
        jedis.sadd("user","zhangxinxin");
        jedis.sadd("user","who");
        //移除noname
        jedis.srem("user","who");
        System.out.println(jedis.smembers("user"));//获取所有加入的value
        System.out.println(jedis.sismember("user", "who"));//判断 who 是否是user集合的元素
        System.out.println(jedis.srandmember("user"));
        System.out.println(jedis.scard("user"));//返回集合的元素个数
    }

    @Test
    public void test() throws InterruptedException {
        //jedis 排序
        //注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
        jedis.del("a");//先清除数据，再加入数据进行测试
        jedis.rpush("a", "1");
        jedis.lpush("a","6");
        jedis.lpush("a","3");
        jedis.lpush("a","9");
        System.out.println(jedis.lrange("a",0,-1));// [9, 3, 6, 1]
        System.out.println(jedis.sort("a")); //[1, 3, 6, 9]  //输入排序后结果
        System.out.println(jedis.lrange("a",0,-1));
    }

    @Test
    public void testRedisPool() {
        JedisUtils.getJedis().set("newname", "中文测试");
        System.out.println(JedisUtils.getJedis().get("newname"));
    }

    @Test
    public void testLambda(){
        List<String> names = Arrays.asList("jianjian","lili","xixi");
        names.forEach((String name)-> {
            name += "name";
            System.out.println(name);
        });
        long countNamesEndI = names.stream().filter(name -> name.endsWith("i")).count();
//        System.out.println(countNamesEndI);
        names.stream().filter(checkIfEndWit("n"))
                .map(name -> name.length())
                .forEach(count -> System.out.println(count + "xxx"));

    }

    @Test
    public void testLambdaMap(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", "jianjian");
        paramMap.put("age", 86);
        paramMap.put("address", "qonglueq");
        paramMap.put("cost", 152686.15d);

        Set<String> keys = paramMap.keySet();
        keys.forEach(key -> System.out.println(paramMap.get(key)));
    }

    /***
     * 返回一个Lambda 表达式
     * @param letter
     * @return
     */
    private static Predicate<String> checkIfEndWit(final String letter){
        return name -> name.endsWith(letter);
    }
}
