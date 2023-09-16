package com.ritesh.redis.pubsub;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Slf4j
@Service
public class RedisSubscriber {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private RedissonClient redissonClient;

    @PostConstruct
    public void subscriber() {
        RTopic rTopic = redissonClient.getTopic("clear-all-cache");
        rTopic.addListener(String.class, (channel, message) -> {
            log.info("Invalidating all cache in every 15 min");
            Collection<String> caches = cacheManager.getCacheNames();
            caches.forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).invalidate());
        });
    }
}
