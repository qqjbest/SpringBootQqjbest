package com.qqj.web.filter;/**
 * @auther qqjbest qqjbest
 * @create 2019-04-29
 */

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 *
 * @auther qjqiu
 * @create 2019-04-29
 */
@Configuration
public class StarsFilterConfiguration {
    /*FilterRegistrationBean 用来配置urlpattern 来确定哪些路径触发filter */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(AuthFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }

    /*使用annotation tag来取代<bean></bean>*/
    @Bean()
    public Filter AuthFilter() {
        return new CorsFilter();
    }
}
