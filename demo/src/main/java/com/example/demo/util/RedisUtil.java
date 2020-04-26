package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    private String redisKeyPrepend = "local";

    private Long redisTtl = 60000L;

    public <T> T storeInCache(String keyName, T value, boolean withTtl) {
        try {
            String finalKey = redisKeyPrepend + "_" + keyName;
            redisTemplate.opsForValue().set(finalKey, value);
            redisTemplate.expire(finalKey, redisTtl, TimeUnit.SECONDS);
            log.info("Adding key in redis");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("redis not working ", e);
        }
        return value;
    }

    public void removeKeyFromCache(String key) {
        String parsedKey = redisKeyPrepend + "_" + key;
        log.info("deleting key in redis");
        Boolean isDeleted = redisTemplate.delete(parsedKey);
        if (isDeleted && redisTemplate.hasKey(parsedKey)) {
            log.info("Unable to delete the key");
        } else {
            log.info("Key deleted successfully");
        }
    }

    public Object getValueFromCache(String keyName, Boolean resetExpiryTime, boolean withTtl) {
        String parsedKey = redisKeyPrepend + "_" + keyName;
        Object value = null;
        if (redisTemplate.hasKey(parsedKey)) {
            value = redisTemplate.opsForValue().get(parsedKey);
        }
        log.info("<-- getValueFromCache {} : {}", parsedKey, value);
        if (resetExpiryTime) {
            storeInCache(keyName, value, withTtl);
        }
        return value;
    }

}
