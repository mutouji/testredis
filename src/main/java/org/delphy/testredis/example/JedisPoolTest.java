package org.delphy.testredis.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisPoolTest {
    private static JedisPool jedisPool;

    private static JedisPoolConfig initPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(300);
        jedisPoolConfig.setMaxTotal(1000);
        // 超时时间
        jedisPoolConfig.setMaxWaitMillis(1000000);
        // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        jedisPoolConfig.setTestOnBorrow(true);
        // 在还会给pool时，是否提前进行validate操作
        jedisPoolConfig.setTestOnReturn(true);
        return jedisPoolConfig;
    }

    public static void prepare() {
        JedisPoolConfig jedisPoolConfig = initPoolConfig();
        // 属性文件读取参数信息
        ResourceBundle bundle = ResourceBundle.getBundle("redis");
        String host = bundle.getString("redis.host");
        int port = Integer.valueOf(bundle.getString("redis.port"));
        // 构造连接池
        jedisPool = new JedisPool(jedisPoolConfig, host, port);
    }

    public static void testSet() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("blog_pool", "java2000_wl");
        } catch (Exception e) {
            // 销毁对象
//            jedisPool.returnBrokenResource(jedis);
//            Assert.fail(e.getMessage());
            e.printStackTrace();
        } finally {
            // 还会到连接池
//            jedisPool.returnResource(jedis);
            if (jedis != null)
                jedis.close();
        }
    }

    public static void testGet() {
        Jedis jedis = null;
        try {
            // 从池中获取一个jedis实例
            jedis = jedisPool.getResource();
            System.out.println(jedis.get("blog_pool"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    public static void main(String[] args) {
        prepare();
        testSet();
        testGet();
    }
}
