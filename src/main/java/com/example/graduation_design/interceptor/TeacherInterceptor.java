package com.example.graduation_design.interceptor;

import com.example.graduation_design.component.EncryptComponent;
import com.example.graduation_design.component.MyToken;
import com.example.graduation_design.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TeacherInterceptor implements HandlerInterceptor {
    @Autowired
    private EncryptComponent encryptComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String json = request.getHeader(MyToken.AUTHORIZATION);
        MyToken myToken = encryptComponent.DecryptToken(json);
        if(myToken.getRole()!= User.Role.TEACHER){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"无权限");
        }

        return true;
    }
}