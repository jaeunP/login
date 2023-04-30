package com.example.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    
    
//    init(): 필터 초기화 메서드, 서블릿 컨테이너가 생성될 때 호출된다
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

//    doFIlter(): 고객의 요청이 올 때 마다 해당 메서드가 호출된다. 필터의 로직을 구현하면 된다
//    ServletRequest request는 HTTP 요청이 아닌 경우까지 고려해서 만든 인터페이스
//    HTTP를 사용하면 HttpServletRequest httpRequest = (HttpServletRequest) request; 와 같이 다운 캐스팅 하면 된다.
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

//        HTTP 요청을 구분하기 위해 요청당 임의의 uuid를 생성해둔다.
        String uuid = UUID.randomUUID().toString();

        try {
            log.info("REQUEST [{}][{}]", uuid, requestURI);
//            다음 필터가 있으면 필터를 호출하고, 필터가 없으면 서블릿을 호출
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }
    }

//    destroy(): 필터 종료 메서드, 서블릿 컨테이너가 종료될 때 호출
    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
