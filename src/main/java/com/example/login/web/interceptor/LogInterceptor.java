package com.example.login.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logId";


    /**
     * 컨트롤러 호출 전에 호출
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();

//      서블릿 필터의 경우 지역변수로 해결이 가능하지만, 스프링 인터셉터는 호출 시점이 완전히 분리됨
//      preHandle에서 지정한 값을 postHandle, afterCompletion에서 함께 사용하려면 어딘가에 담아야함 -> LogInterceptor도 싱글톤처럼 사용되기 때문에 멤버변수 사용은 위험
//      따라서 request에 담아두고 request.getAttribute로 찾아서 사용
        request.setAttribute(LOG_ID,uuid);

//      핸들러 정보는 어떤 핸들러 매핑을 사용하는가에 따라 달라짐
//      스프링을 사용하면 일반적으로 @Controller, @RequestMapping을 활용한 핸들러 맵핑을 사용하는데, 이 경우 핸들러 정보로 HandlerMethod가 넘어옴
//      정적 리소스가 호출되는 경우 ResourceHttpRequestHandler
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;// 호출할 컨트롤러 메서드의 모든 정보가 포함되어 있다.
        }

        log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);

        return true;
    }


    /**
     * 컨트롤러에서 예외가 발생하면 호출되지 않음
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle [{}]", modelAndView);
    }

    /**
     * 항상 호출
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = (String)request.getAttribute(LOG_ID);

        log.info("RESPONSE [{}][{}][{}]", logId, requestURI, handler);
        if (ex != null) {
            log.error("afterCompletion error!!",ex);
        }

    }
}
