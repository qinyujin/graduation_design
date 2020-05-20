package com.example.graduation_design.controller;

import com.example.graduation_design.component.EncryptComponent;
import com.example.graduation_design.component.MyToken;
import com.example.graduation_design.entity.User;
import com.example.graduation_design.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.ExcludeSuperclassListeners;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class loginController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EncryptComponent encryptComponent;

    @Value("${my.teacher}")
    private String TeacherRole;
    @Value("${my.student}")
    private String StudentRole;

    @PostMapping("login")
    public Map login(@RequestBody User user, HttpServletResponse response) {
        User u = userService.getUserByNum(user.getNum());
        if (u != null && encoder.matches(user.getPassword(), u.getPassword())) {
                MyToken myToken = new MyToken(u.getId(), u.getRole());
                response.setHeader(MyToken.AUTHORIZATION, encryptComponent.EncryptToken(myToken));
        } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或者密码错误");
        String role = u.getRole() == User.Role.TEACHER ? TeacherRole : StudentRole;
        return Map.of("user", role);
    }
}