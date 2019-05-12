package com.team2.webservice.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Autowired
    AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //---------------로그인 권한이 필요한 페이지-----------
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/board/*")
                .addPathPatterns("/user/edit")
                .excludePathPatterns("/login");

        //---------------로그인페이지에 대한 인터셉터----------
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/login");
    }
}
