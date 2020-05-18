package com.example.graduation_design;

import com.example.graduation_design.entity.*;
import com.example.graduation_design.repository.*;
import com.example.graduation_design.service.UserService;
import com.example.graduation_design.service.studentService;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.core.support.SurroundingTransactionDetectorMethodInterceptor;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class courseTest {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    directionsRepository directionsRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    studentService studentService;


    @Test
    public void test1() {
        Student student=new Student();
        User u=new User();
        u.setName("Chang");
        u.setNum("2017214228");
        u.setPassword("123456");
        u.setRole(User.Role.STUDENT);
        student.setUser(u);
        student.setWeightScore(90);
studentService.addStudent(student);
    }

}