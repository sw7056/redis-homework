package com.mingyin;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class Hash {

    public static void main(String[] args) {
        Jedis jedis=new Jedis("127.0.0.1",6379);
        Post post=new Post();
        post.setAuthor("sw");
        post.setContent("bb");
        post.setScore("aa");
        Long postId=SavePost(post,jedis);
        System.out.println("保存成功");
        Post post1=getPost(jedis,postId);
        System.out.println(post1);
        System.out.println("获取成功");




    }
     public static Post getPost(Jedis jedis,Long postId){
        Map<String, String> myBlog=jedis.hgetAll("post:"+postId+":data");
        Post post=new Post();
        post.setAuthor(myBlog.get("author"));
        post.setContent(myBlog.get("content"));
        post.setScore(myBlog.get("score"));
        return post;
    }
    public static Long SavePost(Post post,Jedis jedis){
        Long postId =jedis.incr("posts");
        Map<String,String> blog=new HashMap<String, String>();
        blog.put("author",post.getAuthor());
        blog.put("content",post.getContent());
        blog.put("score",post.getScore());
        jedis.hmset("post:"+postId+":date",blog);
        return postId;
    }
    public static void delpost(long postId,Jedis jedis){
        jedis.hdel("Post:"+postId,"author");
        jedis.hdel("Post:"+postId,"content");
        jedis.hdel("Post:"+postId,"score");
        jedis.lrem("list:post",1,"Post:"+postId);
    }
    public static void delpost1(long postid, String key, Jedis jedis){
        jedis.hdel("Post:"+jedis.get("postid"),key);
    }
    public static void UpPost(int postid,String key,String val,Jedis jedis){
        Map<String, String> sw = jedis.hgetAll("Post:" + postid);
        sw.put(key,val);
        jedis.hmset("Post:" + postid,sw);
    }
    public static void setpost(long page,long size,Jedis jedis){
        jedis.lrange("list:post",(page-1)*size,page*size-1);
        System.out.println(jedis.lrange("list:post",(page-1)*size,page*size-1));
    }
}
