package com.qqj.conf;/**
 * @auther qqjbest qqjbest
 * @create 2019-05-06
 */

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @auther qjqiu
 * @create 2019-05-06
 */
@Component
@Data
public class CommonYmlUtil {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;
}
