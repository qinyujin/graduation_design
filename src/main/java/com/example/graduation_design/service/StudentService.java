package com.example.graduation_design.service;

import com.example.graduation_design.component.MyToken;
import com.example.graduation_design.entity.Course;
import com.example.graduation_design.entity.Student;
import com.example.graduation_design.entity.User;
import com.example.graduation_design.entity.directions;
import com.example.graduation_design.repository.StudentRepository;
import com.example.graduation_design.repository.UserRepository;
import com.example.graduation_design.repository.selectedCoursesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class StudentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private selectedCoursesRepository selectedCoursesRepository;

    /**
     * 增添学生
     *
     * @param student
     */
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student getStudentByNum(String num) {
        return studentRepository.findByUser_Num(num);
    }

    /**
     * 全参数更新
     *
     * @param sid
     * @param NewStudent
     * @return
     */
    public Student updateStu(int sid, Student NewStudent) {
        Student student = studentRepository.findById(sid).orElse(null);

        student.getUser().setRole(User.Role.STUDENT);
        studentRepository.save(student);
        userRepository.save(student.getUser());
        return student;
    }

    /**
     * 查看选课
     *
     * @return
     */
    public List<Course> getCourses(int sid) {
        return selectedCoursesRepository.listByStudentId(sid);
    }

}