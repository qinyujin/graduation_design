package com.example.graduation_design.component;

import com.example.graduation_design.entity.Teacher;
import com.example.graduation_design.entity.User;
import com.example.graduation_design.repository.StudentRepository;
import com.example.graduation_design.service.TeacherService;
import com.example.graduation_design.service.UserService;;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class initComponent implements InitializingBean {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private updateSuitable updateSuitable;


    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化管理员账号，默认为账号密码都是1001的BO老师
        final String num = "1001";
        User user = userService.getUserByNum(num);
        if (user == null) {
            User u = new User();
            u.setName("王波");
            u.setNum(num);
            u.setPassword(encoder.encode(num));
            u.setRole(User.Role.TEACHER);
            Teacher t = new Teacher();
            t.setUser(u);

            teacherService.addTeacher(t);
        }

        updateSuitable.update();

    }
}