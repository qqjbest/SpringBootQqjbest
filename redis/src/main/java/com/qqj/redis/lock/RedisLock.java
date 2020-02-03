package com.qqj.redis.lock;

import org.json.JSONObject;
import org.redisson.Redisson;
import org.redisson.RedissonLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {

    //自旋重试时长  2秒
    private static Long SPIN_RETRY_TIME = 2L;

    //锁过期时长 30秒
    private static Integer LOCK_EXPIRE_TIME = 30;

    @Autowired
    private RedisTemplate redisTemplate;

    private ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public boolean tryLock(String lockKey){
        boolean flag = false;
        String lockValue = UUID.randomUUID().toString();
        System.out.println("lockValue "+lockValue);
        threadLocal.set(lockValue);
        Boolean lock = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, Duration.ofSeconds(LOCK_EXPIRE_TIME));
        if(!lock){
            while(true){
                //是否需要sleep？ 要不会导致频繁的操作redis
                boolean lockSuccess = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, Duration.ofSeconds(LOCK_EXPIRE_TIME));
                if(lockSuccess){
                    flag = true;
                    break;
                }
                try {
                    //设置自旋重试时间
                    TimeUnit.SECONDS.sleep(SPIN_RETRY_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    public boolean unLock(String lockKey){
        boolean flag = false;
        System.out.println(threadLocal.get());
        if(threadLocal.get().equals(redisTemplate.opsForValue().get(lockKey).toString())){
            flag = redisTemplate.delete(lockKey);
        }
        return flag;
    }
}
