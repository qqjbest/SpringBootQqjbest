package com.qqj.conf;/**
 * @auther qqjbest qqjbest
 * @create 2019-05-07
 */

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @auther qjqiu
 * @create 2019-05-07
 */
@Component
@Data
public class CacheProperties {
    @Value("${cache.default.expire-time:1800}")
    private int defaultExpireTime;

    @Value("${cache.test.expire-time:180}")
    private int testExpireTime;

    @Value("${cache.test.name:test}")
    private String testCacheName;
}
