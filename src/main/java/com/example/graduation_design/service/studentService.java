package com.example.graduation_design.service;

import com.example.graduation_design.entity.Student;
import com.example.graduation_design.entity.User;
import com.example.graduation_design.entity.directions;
import com.example.graduation_design.repository.StudentRepository;
import com.example.graduation_design.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;

    /**
     * 增添学生
     * @param student
     */
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

}