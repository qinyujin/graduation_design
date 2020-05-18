package com.example.graduation_design;

import com.example.graduation_design.entity.*;
import com.example.graduation_design.repository.*;
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


    @Test
    public void test1(){

    }

}