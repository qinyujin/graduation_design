package com.example.graduation_design.component;

import com.example.graduation_design.entity.Teacher;
import com.example.graduation_design.entity.User;
import com.example.graduation_design.service.TeacherService;
import com.example.graduation_design.service.UserService;;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class initComponent implements InitializingBean  {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private TeacherService teacherService;

    @Override
    public void afterPropertiesSet() throws Exception {
        final String num="1001";
        User user=userService.getUserByNum(num);
        if(user==null){
            User u=new User();
            u.setName("王波");
            u.setNum(num);
            u.setPassword(encoder.encode(num));
            u.setRole(User.Role.TEACHER);
            Teacher t=new Teacher();
            t.setUser(u);

            teacherService.addTeacher(t);
        }
    }
}