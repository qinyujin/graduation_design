package com.example.graduation_design.service;

import com.example.graduation_design.entity.Course;
import com.example.graduation_design.entity.Student;
import com.example.graduation_design.entity.selectedCourses;
import com.example.graduation_design.repository.CourseRepository;
import com.example.graduation_design.repository.StudentRepository;
import com.example.graduation_design.repository.selectedCoursesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private selectedCoursesRepository selectedCoursesRepository;

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void addStu(int cid, double score, int sid) {
        Course c = courseRepository.findById(cid).orElse(null);
        Student s = studentRepository.findById(sid).orElse(null);
        if (c != null && s != null && selectedCoursesRepository.findByStudentIdAndCourseId(sid, cid) == null) {
            selectedCourses sc = new selectedCourses();
            sc.setCourse(c);
            sc.setStudent(s);
            sc.setScore(score);
            selectedCoursesRepository.save(sc);
            log.debug("{}", "增加课程学生记录");
        } else if (selectedCoursesRepository.findByStudentIdAndCourseId(sid, cid) != null) log.debug("{}", "该学生记录已经存在");
        else log.debug("{}", "学生或者课程信息不存在");
    }

    public void updateCourseStu(int cid, int sid, double score) {
        selectedCourses sc = selectedCoursesRepository.findByStudentIdAndCourseId(sid, cid);
        if (sc != null) {
            sc.setScore(score);
            selectedCoursesRepository.save(sc);
        } else log.debug("{}", "该记录不存在");
    }

    public Course getCourseByName(String name) {
        return courseRepository.findByCourseName(name);
    }

    public Course getCourseById(int cid) {
        return courseRepository.findById(cid).orElse(null);
    }

    /**
     * 设置课程权重
     * @param weight
     * @param cid
     */
    public void setWeight(double lowest,double weight, int cid) {
        Course c=courseRepository.findById(cid).orElse(null);

        if(c!=null){
            c.setWeight(weight);
            c.setLowestScore(lowest);
            courseRepository.save(c);
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"该课程不存在");
    }
}