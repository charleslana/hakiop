package com.charles.hakiop.interceptor;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String theMethod = request.getMethod();
        if (HttpMethod.GET.matches(theMethod) || HttpMethod.POST.matches(theMethod)) {
            return true;
        }
        response.sendError(HttpStatus.METHOD_NOT_ALLOWED.value());
        return false;
    }
}
