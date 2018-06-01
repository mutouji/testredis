package org.delphy.testredis.example;

import io.netty.util.internal.ConcurrentSet;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

public class RedissonExample {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379/");
//        config.useSingleServer().setConnectionPoolSize(10);
        RedissonClient redisson = Redisson.create(config);
        System.out.println("链接redis");
        ConcurrentMap<String, Object> map = redisson.getMap("FirstMap");
        map.put("czy", "男");
        map.put("yinan", "男");
        map.put("pengfei", "女");

        ConcurrentMap resultmap = redisson.getMap("FirstMap");
        System.out.println("resultmap = " + resultmap.keySet());

        Set myset = redisson.getSet("MySet");
        myset.add("czy");
        myset.add("pengfei");

        Set resultSet = redisson.getSet("MySet");
        System.out.println("resultset = " + resultSet.size());

        //3.测试Queue队列
        Queue myQueue = redisson.getQueue("FirstQueue");
        myQueue.add("wuguowei");
        myQueue.add("lili");
        myQueue.add("zhangsan");
        System.out.println(myQueue.peek());
        System.out.println(myQueue.poll());

        Queue resultQueue = redisson.getQueue("FirstQueue");
        System.out.println("resultQueue === " + resultQueue);
        // 关闭连接
        redisson.shutdown();
    }
}
