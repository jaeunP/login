package com.example.login.web.login;

import com.example.login.web.argumentresolver.LoginMemberArgumentResolver;
import com.example.login.web.filter.LogFilter;
import com.example.login.web.filter.LoginCheckFilter;
import com.example.login.web.interceptor.LogInterceptor;
import com.example.login.web.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

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

    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LogInterceptor())   //인터셉터 등록
                .order(1)   //인터셉터의 호출 순서를 지정. 낮을 수록 먼저 호출
                .addPathPatterns("/**")     //인터셉터를 적용할 URL 패턴을 지정
                .excludePathPatterns("/css/**", "/*.ico", "/error");    //인터셉터에서 제외할 패턴을 지정

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/members/add", "/login", "/logout",
                        "/css/**", "/*.ico", "/error");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }
}
