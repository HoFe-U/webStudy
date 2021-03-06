package com.nhnacademy.springmvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class LoginCheckInterceptor implements HandlerInterceptor {
    private final List<String> urlList = new ArrayList();
    public void blackList(){
        urlList.add("/WEB-INF/view/thymeleaf/login.html");
        urlList.add("/login");
        urlList.add("thymeleaf/login");
        urlList.add("/students");
        urlList.add("/students/0");
        urlList.add("/home");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        blackList();
        if (Objects.isNull(session) && !(urlList.contains(request.getRequestURI()))){
            response.sendRedirect("/login");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        urlList.clear();
    }

}
