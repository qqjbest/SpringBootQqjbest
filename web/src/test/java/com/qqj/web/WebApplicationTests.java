package com.qqj.web;

import com.qqj.entity.Admin;
import com.qqj.service.IAdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

	@Autowired
	private IAdminService adminService;

	@Test
	public void contextLoads() {
		Map<String,Object> params = new HashMap<>();
		params.put("account","");
		List<Admin> admins = adminService.getAllByMap(params);
		System.out.println(admins.size());

	}

}
