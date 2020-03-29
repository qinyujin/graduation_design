package com.example.graduation_design;

import com.example.graduation_design.entity.Course;
import com.example.graduation_design.entity.Student;
import com.example.graduation_design.entity.Teacher;
import com.example.graduation_design.entity.directions;
import com.example.graduation_design.repository.CourseRepository;
import com.example.graduation_design.repository.StudentRepository;
import com.example.graduation_design.repository.TeacherRepository;
import com.example.graduation_design.repository.directionsRepository;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.core.support.SurroundingTransactionDetectorMethodInterceptor;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;

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


    @Test
    public void testCourse() {
//        for (Course course : courseRepository.findAll()) {
//            System.out.println(course.getCourseName());
//        }
//
//        System.out.println(courseRepository.findById(2).getCourseName());
//
//        courseRepository.addCourse("Zelda", 100, 1.0f);
//
//        courseRepository.updateCourseById("BO's Springboot", 3);
        courseRepository.deleteCourseById(8);
    }

    @Test
    public void testDirections() {

//        directionsRepository.addDirections("Backup");
//        directionsRepository.addDirections("BigData");
//        directionsRepository.addDirections("Web");

//        for (directions directions : directionsRepository.findAll()) {
//            System.out.println(directions.getDetail());
//        }

//        System.out.println(directionsRepository.findById(2).getDetail());
//
//        Teacher t=teacherRepository.findById(2);
//        directionsRepository.updateDirectionsById(t,3);

//        directionsRepository.deleteDirectionsById(2);
    }

    @Test
    public void testStudent() {
//        studentRepository.addStudent(2017214228, "Qinyujin");

//        studentRepository.deleteStudentById(2017214229);

//        Teacher t = teacherRepository.findById(3);
//        studentRepository.updateStudentById(t,2017214228 );

        System.out.println(studentRepository.findById(2017214228).getTeacher().getName());

    }

    @Test
    public void testTeacher(){
//teacherRepository.updateTeacherById("Rookie", 6, 0, 2);

//        teacherRepository.addTeacher("Lilinhui", 10);

//        teacherRepository.deleteTeacherById(4);

//        teacherRepository.findAll().forEach(a -> System.out.println(a.getName()));

Teacher t=        teacherRepository.findById(3);

        System.out.println(t.getName());
    }


}