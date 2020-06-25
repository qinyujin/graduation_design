package com.example.graduation_design.service;

import com.example.graduation_design.entity.Student;
import com.example.graduation_design.entity.Teacher;
import com.example.graduation_design.entity.directions;
import com.example.graduation_design.repository.StudentRepository;
import com.example.graduation_design.repository.TeacherRepository;
import com.example.graduation_design.repository.directionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private directionsRepository directionsRepository;
    @Autowired
    private StudentRepository studentRepository;

    /**
     * 增添老师，默认没有学生数和范围数
     *
     * @param teacher
     */
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    /**
     * 设置可指导学生数
     *
     * @param tid
     * @param stuNum
     */
    public void updateTeacher(int tid, int stuNum) {
        Teacher t = teacherRepository.findById(tid).orElse(null);
        if (t != null) {
            t.setTotalStudents(stuNum);
            teacherRepository.save(t);
        } else log.debug("{}", "没有找到该用户");
    }

    public void addDirections(directions d) {
        directionsRepository.save(d);
    }

    public void updateDirection(int did, String detail) {
        directions d = directionsRepository.findById(did).orElse(null);
        if (d != null) {
            d.setDetail(detail);
            directionsRepository.save(d);
        } else log.debug("{}", "未找到这个方向");
    }

    public void addTeacherStu(int tid, int sid) {
        Student s = studentRepository.findById(sid).orElse(null);
        Teacher t = teacherRepository.findById(tid).orElse(null);
        if (s != null && t != null && s.getTeacher() == null) {
            s.setTeacher(t);
            studentRepository.save(s);
        } else if (s.getTeacher().getId() == tid) log.debug("{}", "该学生已选择该导师");
        else log.debug("{}", "选择的导师或者学生不存在");
    }

    /**
     * 根据名字得到教师
     *
     * @param num
     * @return
     */
    public Teacher getTeacherByNum(String num) {
        Teacher t = teacherRepository.findByUser_Num(num);
        return t;
    }

    /**
     * 根据id得到教师
     *
     * @param id
     * @return
     */
    public Teacher getTeacherById(int id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public directions getDirectionById(int id) {
        return directionsRepository.findById(id).orElse(null);
    }

    public void updateSuitableStus(int amount) {
        Teacher teacher = teacherRepository.findById(1).orElse(null);

        teacher.setSuitableStudents(amount);
        teacherRepository.save(teacher);
    }
}