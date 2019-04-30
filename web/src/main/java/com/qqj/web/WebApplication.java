package com.qqj.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.qqj.mapper")
@ComponentScan({"com.qqj.service","com.qqj.shiro.*","com.qqj.web.controller","com.qqj.web.*"})
//@ComponentScan({"com.qqj.shiro.realm"})
public class WebApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
