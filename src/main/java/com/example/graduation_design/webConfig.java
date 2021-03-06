package com.example.graduation_design;

import com.example.graduation_design.interceptor.TeacherInterceptor;
import com.example.graduation_design.interceptor.loginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {
    @Autowired
    private loginInterceptor loginInterceptor;
    @Autowired
    private TeacherInterceptor teacherInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/resetPassword");

        registry.addInterceptor(teacherInterceptor)
                .addPathPatterns("/api/teacher/**");
    }
}