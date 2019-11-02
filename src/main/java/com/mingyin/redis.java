package com.mingyin;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class redis {
    public static void main(String[] args) {
                Jedis jedis = new Jedis("127.0.0.1", 6379);
                Post post = new Post();
                post.setAuthor("cj");
                post.setContent("a");
                post.setScore("b");
                Long postid=savePost(post,jedis);
                System.out.println("保存成功");
                Post post1=getPost(jedis,postid);
                System.out.println(post1);
                System.out.println("获取成功");



    }

    private static Post getPost(Jedis jedis, Long postid) {
        return null;
    }

    private static Long savePost(Post post, Jedis jedis) {
        Long posts=jedis.incr("posts");
        String poststr=JSON.toJSONString(post);
        jedis.set("post:"+posts+":data",poststr);
        return posts;
    }

    public static void addPost(Post post,Jedis jedis){
        long postid =jedis.incr("post");
        String postPost= JSON.toJSONString(post);
        String posts=post.getAuthor()+postid+post.getScore();
        jedis.set(posts,postPost);
    }

    public static void delpost(String postName,Jedis jedis){
        jedis.del(postName);
    }
    public static void upAuthor(Post post,String postName, Jedis jedis){
        post.setAuthor("cj");
        String post1=JSON.toJSONString(post);
        jedis.set(postName,post1);
    }
}
