package com.qqj.web;

import com.alibaba.fastjson.parser.ParserConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.qqj.mapper")
@ComponentScan({"com.qqj.conf","com.qqj.redis.config","com.qqj.service","com.qqj.shiro.*","com.qqj.web.controller","com.qqj.web.*"})
@EnableCaching
//@ComponentScan({"com.qqj.shiro.realm"})
public class WebApplication {
	public static void main(String[] args) {
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		SpringApplication.run(WebApplication.class, args);
	}
}
