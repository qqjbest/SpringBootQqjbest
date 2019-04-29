package com.qqj.conf;/**
 * @auther qqjbest qqjbest
 * @create 2019-04-29
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @auther qjqiu
 * @create 2019-04-29
 */
@Data
@Component
@ConfigurationProperties(prefix = "common")
public final class CommonProperties {

    private String website;

    private String serviceIp;

    private String baseImg;

}
