package com.ritesh.redis.cache;

import com.ritesh.redis.entity.Employee;
import com.ritesh.redis.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CacheServiceDao {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private RedissonClient redissonClient;

    @PostConstruct
    public void clearCacheEvery15min() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> {
            RTopic rTopic = redissonClient.getTopic("clear-all-cache");
            log.info("Scheduling clear cache task after every 15min");
            rTopic.publish("clear cache");
        };
        executorService.scheduleWithFixedDelay(task, 0, 10, TimeUnit.SECONDS);
    }

    @Cacheable(cacheNames = CacheResourceConfig.CacheName.EMPLOYEE_REDISSON_CACHE, key = "'all-employee'")
    public List<Employee> findAll() {
        return repository.findAll();
    }
}
