package com.qqj.redis;

import com.qqj.redis.lock.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

	@Autowired
	private RedisLock redisLock;

	@Test
	public void lockTest() {
		redisLock.tryLock("abc");
		redisLock.tryLock("abc");
		redisLock.unLock("abc");
	}

	@Test
	public void unLockTest(){

	}
}
