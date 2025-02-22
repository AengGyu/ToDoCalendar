package com.aenggyu.todoCalendar;

import com.aenggyu.todoCalendar.interceptor.AdminCheckInterceptor;
import com.aenggyu.todoCalendar.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/",
                        "/login",
                        "/logout",
                        "/members/join",
                        "/members/welcome/{memberId}",
                        "/css/**", "/js/**", "/img/**");

        registry.addInterceptor(new AdminCheckInterceptor())
                .order(2)
                .addPathPatterns("/members", "/events/all");
    }
}
