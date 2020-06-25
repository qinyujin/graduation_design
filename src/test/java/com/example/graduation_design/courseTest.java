package com.example.graduation_design;

import com.example.graduation_design.entity.*;
import com.example.graduation_design.repository.*;
import com.example.graduation_design.service.TeacherService;
import com.example.graduation_design.service.UserService;
import com.example.graduation_design.service.CourseService;
import com.example.graduation_design.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class courseTest {
    @Value("${my.salt}")
    private String salt;

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
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;
    @Autowired
    PasswordEncoder encoder;


    @Test
    public void addStu() {
        User u = new User();
        u.setName("WangBin");
        u.setNum("2017214229");
        u.setPassword(encoder.encode("2017214229"));
        u.setRole(User.Role.STUDENT);
        Student s = new Student();
        s.setUser(u);
        studentService.addStudent(s);
    }

    @Test
    public void test1() {
        User u = new User();
        u.setName("BO");
        u.setPassword("123456");
        u.setRole(User.Role.TEACHER);
        u.setNum("123");
        Teacher t = new Teacher();
        t.setUser(u);

        teacherService.addTeacher(t);
    }

    @Test
    public void updatePwd() {
        userService.updatePwd(3, "我超喜欢常艳超");
    }

    @Test
    public void updateTeacher() {
        teacherService.updateTeacher(4, 10);
    }

    @Test
    public void addDirections() {
        directions direction = new directions();
        direction.setDetail("Java开发");
        teacherService.addDirections(direction);
    }

    @Test
    public void updateDirection() {
        teacherService.updateDirection(1, "人工智能");
    }

    @Test
    public void adCourse() {
        Course c = new Course();
        c.setCourseName("Java");
        c.setWeight(60.0);
        c.setLowestScore(50.0);
        courseService.addCourse(c);
    }

    @Test
    public void addCourseStu() {
        courseService.addStu(1, 80.0, 10);
    }

    @Test
    public void updateCourseStu() {
        courseService.updateCourseStu(1, 10, 85);
    }

    @Test
    public void addTeacherStu() {
        teacherService.addTeacherStu(4, 3);
    }


    @Test
    public void testAny() {
        System.out.println(salt);
    }
}