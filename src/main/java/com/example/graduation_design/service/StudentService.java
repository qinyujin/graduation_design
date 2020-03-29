package com.example.graduation_design.service;

import com.example.graduation_design.entity.Student;
import com.example.graduation_design.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {

    void addStudent(int stuNo,String name);
    void deleteStudent(int sno);
    List<Student> findAll();
    Student findById(int sno);
    void updateStudent(Teacher t);//学生可以更新成功选择的导师

}