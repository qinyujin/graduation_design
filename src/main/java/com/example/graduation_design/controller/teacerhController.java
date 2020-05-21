package com.example.graduation_design.controller;

import com.example.graduation_design.entity.Teacher;
import com.example.graduation_design.entity.User;
import com.example.graduation_design.service.TeacherService;
import com.example.graduation_design.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/teacher/")
public class teacerhController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;

    /**
     * 添加老师
     * @param user
     * @return
     */
    @PostMapping("addTeacher")
    public Map postTeacher(@RequestBody User user) {
        User u=userService.getUserByNum(user.getNum());
        Teacher t=new Teacher();
     if(u==null){
         user.setPassword(encoder.encode(user.getPassword()));
         t.setUser(user);
         teacherService.addTeacher(t);
     }
     else throw new ResponseStatusException(HttpStatus.FORBIDDEN,"该老师已经存在!" );
     return Map.of("Teacher",t);
    }
}