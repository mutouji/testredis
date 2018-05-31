package org.delphy.testredis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestredisApplication.class)
public class TestredisApplicationTests {

//	@Autowired
//	RedisConnectionFactory factory;

//	@Autowired
//	RedisTemplate<String, Object> template;

	@Autowired
	RedissonClient redissonClient;

	@Test
	public void testRedissonClient() {
		RMapCache<String, Integer> mapCache = redissonClient.getMapCache("test");
		// with ttl = 10 seconds
		Integer prevValue = mapCache.put("1", 10, 10, TimeUnit.SECONDS);
		// with ttl = 15 seconds and maxIdleTime = 5 seconds
		Integer prevValue2 = mapCache.put("2", 20, 5, TimeUnit.SECONDS, 5, TimeUnit.SECONDS);
	}

//	@Test
//	public void testRedisCF() {
//		RedisConnection conn = factory.getConnection();
//		conn.set("hello".getBytes(), "world".getBytes());
//		System.out.println(new String(conn.get("hello".getBytes())));
//	}

//	@Test
//	public void testRedis() {
//		// 得到一个链接
//		RedisConnection conn = factory.getConnection();
//		conn.set("hello".getBytes(), "world".getBytes());
//		System.out.println(new String(conn.get("hello".getBytes())));
//	}

//	@Test
//	public void testRedisTemplate() {
//		template.opsForValue().set("key1", "value1");
//		System.out.println(template.opsForValue().get("key1"));
//	}

//	@Test
//	public void testRedisTemplateList() {
//		Product product1  = new Product(1, "洗发水", "100ml");
//		Product product2  = new Product(2, "洗面奶", "200ml");
//		//依次从尾部添加元素
//		template.opsForList().rightPush("pruduct", product1);
//		template.opsForList().rightPush("pruduct", product2);
//		//查询索引0到商品总数-1索引（也就是查出所有的商品）
//		List<Object> prodList = template.opsForList().range("pruduct", 0,template.opsForList().size("pruduct")-1);
//		for(Object obj:prodList){
//			System.out.println((Product)obj);
//		}
//		System.out.println("产品数量:"+template.opsForList().size("pruduct"));
//	}

	@Test
	public void contextLoads() {
	}

}
