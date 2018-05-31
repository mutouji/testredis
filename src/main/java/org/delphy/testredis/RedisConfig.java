package org.delphy.testredis;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//@Component
//@EnableCaching
public class RedisConfig {
//    @Bean
//    public RedisConnectionFactory redisCF(){
//        //如果什么参数都不设置，默认连接本地6379端口
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setPort(6379);
//        factory.setHostName("localhost");
//        return factory;
//    }

    /*
    *
    * Spring Data Redis提供了两个模板：
　　      RedisTemplate
　　      StringRedisTemplate
    * */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
//        //创建一个模板类
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        //将刚才的redis连接工厂设置到模板类中
//        template.setConnectionFactory(factory);
//        return template;
//    }

//  RedisTemplate会默认使用JdkSerializationRedisSerializer，这意味着key和value都会通过Java进行序列化。StringRedisTemplate默认会使用StringRedisSerializer
//  例如，假设当使用RedisTemplate的时候，我们希望将Product类型的value序列化为JSON，而key是String类型。RedisTemplate的setKeySerializer()和setValueSerializer()方法就需要如下所示：
//    @Bean
//    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
//        // 创建一个模板类
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        // 将刚才的redis连接工厂设置到模板类中
//        template.setConnectionFactory(factory);
//        // 设置key的序列化器
//        template.setKeySerializer(new StringRedisSerializer());
//        // 设置value的序列化器
//        //使用Jackson 2，将对象序列化为JSON
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        //json转对象类，不设置默认的会将json转成hashmap
////        ObjectMapper om = new ObjectMapper();
////        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
////        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
////        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//
//        return template;
//    }

    /**
     *
     * 首先在我们的RedisConfig这个类上加上@EnableCaching这个注解。

     这个注解会被spring发现，并且会创建一个切面（aspect） 并触发Spring缓存注解的切点（pointcut） 。 根据所使用的注解以及缓存的状态， 这个切面会从缓存中获取数据， 将数据添加到缓存之中或者从缓存中移除某个值。

     接下来我们需要申明一个缓存管理器的bean，这个作用就是@EnableCaching这个切面在新增缓存或者删除缓存的时候会调用这个缓存管理器的方法
     *
     * 申明缓存管理器，会创建一个切面（aspect）并触发Spring缓存注解的切点（pointcut）
     * 根据类或者方法所使用的注解以及缓存的状态，这个切面会从缓存中获取数据，将数据添加到缓存之中或者从缓存中移除某个值

     * @return
     */
//    @Bean
//    public RedisCacheManager cacheManager(RedisTemplate redisTemplate) {
//        return new RedisCacheManager(redisTemplate);
//    }

//    @Bean
//    public KeyGenerator keyGenerator() {
//        return new KeyGenerator() {
//            public Object generate(Object target, Method method, Object... params) {
//                StringBuilder sb = new StringBuilder();
//                sb.append(target.getClass().getName());
//                sb.append(method.getName());
//                for (Object obj : params) {
//                    sb.append(obj.toString());
//                }
//                return sb.toString();
//            }
//        };
//    }



//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        return template;
//    }
//
//    @Bean
//    public CacheManagerCustomizer<RedisCacheManager> cacheManagerCustomizer() {
//        return new CacheManagerCustomizer<RedisCacheManager>() {
//            @Override
//            public void customize(RedisCacheManager cacheManager) {
//                cacheManager.setUsePrefix(true);
//
//                Map<String, Long> expires = new HashMap<>();expires.put("myLittleCache", 12L*60*60);  // 设置过期时间 key is cache-name
//                expires.put("myBiggerCache", 24L*60*60);
//                cacheManager.setExpires(expires);  // expire per cache
//
//                cacheManager.setDefaultExpiration(24**60);// 默认过期时间：24 hours
//            }
//        };
//    }

}
