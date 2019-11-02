package com.mingyin;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    private static Jedis jedis;
    static {
        jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis);
    }
    public static void main(String[] rgs){
        System.out.println(jedis.get("k1"));
        jedis.set("k5","v5");
        System.out.println(jedis.get("k5"));
        System.out.println(jedis.get("keyll"));
        Map<String,String> map=new HashMap<String, String>();
        map.put("name","李三");
        jedis.hmset("map",map);
        List<String> list=jedis.hmget("map","name" );
        System.out.println(list);
    }
}
