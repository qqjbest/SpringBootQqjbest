package com.qqj.redis;

import com.qqj.redis.lock.RedisLock;
import com.qqj.redis.redisson.RedissonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

	@Autowired
	private RedisLock redisLock;

	@Autowired
	private RedissonService redissonService;

	@Test
	public void lockTest() {
		redisLock.tryLock("abc");
		redisLock.tryLock("abc");
	}

	@Test
	public void unLockTest() throws IOException {
		redisLock.unLock("abc");
	}

	@Test
	public void redissonLock(){
		try{
			redissonService.lockDemo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
