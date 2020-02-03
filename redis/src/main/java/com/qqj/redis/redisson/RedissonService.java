package com.qqj.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RedissonService implements InitializingBean {
    private RedissonClient redissonClient;

    /**
     * 锁测试
     *
     * @throws IOException
     */
    public void lockDemo() throws IOException {
        RLock rLock = redissonClient.getLock("abcd");
        rLock.lock();
        //business Handle
        System.out.println(123);
        rLock.unlock();
    }

    /**
     * 初始化redisClient
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // 从配置中获取锁
        //集群模式
//        InputStream in = getClass().getClassLoader().getResourceAsStream("clusterServersConfig.yaml");

        //单个redis模式
        InputStream in = getClass().getClassLoader().getResourceAsStream("singleServerConfig.yaml");
        Config config = Config.fromYAML(in);
        redissonClient = Redisson.create(config);
    }
}
