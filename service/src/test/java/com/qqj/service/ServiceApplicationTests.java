package com.qqj.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.qqj.mapper")
@EnableCaching
@ComponentScan({"com.qqj.conf","com.qqj.redis.config","com.qqj.service","com.qqj.shiro.*","com.qqj.web.controller","com.qqj.web.*"})
public class ServiceApplicationTests {

	@Autowired
	private IAdminService adminService;


	@Test
	public void contextLoads() {
		System.out.println(adminService.getAdminById(1125951616483082241L));
	}

}
