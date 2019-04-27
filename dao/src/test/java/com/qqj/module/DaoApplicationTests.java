package com.qqj.module;

import com.qqj.entity.Admin;
import com.qqj.module.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.qqj.module.mapper*")
public class DaoApplicationTests {

	@Autowired
	private AdminMapper adminMapper;

	@Test
	public void testSelectById(){
		Admin admin = adminMapper.selectById(1L);
		System.out.println(1);
	}

}
