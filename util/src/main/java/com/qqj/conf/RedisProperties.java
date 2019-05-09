package com.qqj.conf;
/**
 * @auther qqjbest qqjbest
 * @create 2019-05-06
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @auther qjqiu
 * @create 2019-05-06
 */
@Component
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
    private String host;

    private String port;

    private String password;
}
