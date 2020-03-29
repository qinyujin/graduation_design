package com.example.graduation_design.service;

import com.example.graduation_design.entity.Course;
import com.example.graduation_design.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeacherService {
    void addTeacher(String name,int totalStudents);
    void deleteTeacher(int id);
    List<Teacher> findAll();
    Teacher findById(int id);
    void updateTeacher(int suitableStudents,int totalStudents,int id);

}