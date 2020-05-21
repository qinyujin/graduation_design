package com.example.graduation_design.interceptor;

import com.example.graduation_design.component.EncryptComponent;
import com.example.graduation_design.component.MyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class loginInterceptor implements HandlerInterceptor {
    @Autowired
    private EncryptComponent encryptComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader(MyToken.AUTHORIZATION);
        if (auth != null) {
            MyToken myToken = encryptComponent.DecryptToken(auth);
            request.setAttribute(MyToken.UID, myToken.getUid());
            request.setAttribute(MyToken.ROLE, myToken.getRole());
        }
        else  throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED,"未登录");

        return true;
    }
}