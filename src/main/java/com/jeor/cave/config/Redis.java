package com.jeor.cave.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/24 11:14
 */
@Slf4j
@Component
@Configuration
@EnableCaching
public class Redis {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        redisTemplate.setValueSerializer(genericFastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(genericFastJsonRedisSerializer);
        return redisTemplate;
    }

    @Bean
    @Primary
    public CacheManager cacheManager(RedisTemplate<String, Object> template) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getStringSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getValueSerializer()))
                .entryTtl(Duration.ofHours(8))
                .disableCachingNullValues();
        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(template.getConnectionFactory())
                .cacheDefaults(redisCacheConfiguration)
                .transactionAware()
                .build();
    }


    @Bean
    public CacheErrorHandler cacheErrorHandler() {

        log.info("初始化 -> [{}]", "Redis CacheErrorHandler");
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                log.error("Redis occur handleCacheGetError：key -> [{}]", key, exception);
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                log.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", key, value, exception);
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                log.error("Redis occur handleCacheEvictError：key -> [{}]", key, exception);
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                log.error("Redis occur handleCacheClearError：", exception);
            }
        };
    }

    @Bean("redisKeyGenerator")
    public KeyGenerator keyGenerator(){
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder(method.getName());
            if (params.length == 0) {
                return sb.toString();
            }
            sb.append(":");
            for (Object param : params) {
                if (param != null) {
                    sb.append(param);
                }else {
                    sb.append("-");
                }
                sb.append("_");
            }
            if (sb.lastIndexOf("_") == sb.length() - 1) {
                sb.delete(sb.length() - 1, sb.length());
            }
            return sb.toString();
        };
    }
}
