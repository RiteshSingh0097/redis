package com.ritesh.redis.cache;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CacheResourceConfig {

    @Autowired
    RedissonClient client;

    @Bean
    public CacheManager cacheManager() {
        CacheConfig cacheConfig = new CacheConfig();
        cacheConfig.setMaxSize(10000);
        cacheConfig.setTTL(600000);
        Map<String, CacheConfig> cacheMap = new HashMap<>();
        cacheMap.put(CacheName.EMPLOYEE_REDISSON_CACHE, cacheConfig);
        return new RedissonSpringCacheManager(client, cacheMap);
    }

    public static class CacheName {
        public static final String EMPLOYEE_REDISSON_CACHE = "EMPLOYEE_REDISSON_CACHE";
        public static final String EMPLOYEE_CUSTOM_CACHE = "EMPLOYEE_CUSTOM_CACHE";
    }
}
