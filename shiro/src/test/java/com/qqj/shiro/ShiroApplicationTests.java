package com.qqj.shiro;

import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.qqj.*")
public class ShiroApplicationTests {

	/*@Autowired
	private IAdminService adminService;

	@Test
	public void contextLoads() {
		Admin admin = adminService.getById(1L);
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin.getAccount(), // 用户名
				admin.getPassword(), // 密码
				ByteSource.Util.bytes(admin.getCredentialsSalt()), // salt=username+salt
				getName() // realm name
		);
	}*/

}
