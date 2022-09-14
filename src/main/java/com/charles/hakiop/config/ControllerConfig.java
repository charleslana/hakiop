package com.charles.hakiop.config;

import com.charles.hakiop.interceptor.ControllerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ControllerConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ControllerInterceptor()).addPathPatterns("/api/character*/**");
    }
}
