//package com.company.project.configurer;
//
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Swagger 拦截器
// * @description 用于禁用swagger，当swagger.disabled为true时重定向至${swagger.redirect-uri}
// * @author Caesar Liu
// * @date 2019/4/24 18:23
// */
////@Slf4j
////@Component
//public class SwaggerInterceptor implements HandlerInterceptor {
//
//    @Value("${swagger.disabled}")
//    private Boolean disabledSwagger;
//
//    @Value("${swagger.redirect-uri}")
//    private String redirectUri;
//
//    private static final Logger log = LoggerFactory.getLogger(SwaggerInterceptor.class);
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        if(!disabledSwagger) return Boolean.TRUE;
//        String uri = redirectUri;
//        if(uri == null || uri.trim().length() == 0)
//            uri = "/";
//        try {
//            response.sendRedirect(uri);
//        } catch (IOException e) {
//            log.error(String.format("Redirect to '%s' for swagger throw an exception : %s", uri, e.getMessage()), e);
//        }
//        return Boolean.FALSE;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//    }
//}
