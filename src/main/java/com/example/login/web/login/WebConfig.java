package com.example.login.web.login;

import com.example.login.web.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//        등록할 필터를 지정
        filterRegistrationBean.setFilter(new LogFilter());
//        필터는 체인으로 동작하므로 순서가 필요하다. 숫자가 낮을 수록 먼저 동작한다.
        filterRegistrationBean.setOrder(1);
//        필터를 적용할 URL 패턴을 지정한다. 한번에 여러 패턴을 지정할 수 있다
//        /* : 모든 요청에 적용
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
