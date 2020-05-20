package com.example.graduation_design.service;

import com.example.graduation_design.entity.Course;
import com.example.graduation_design.entity.Student;
import com.example.graduation_design.entity.selectedCourses;
import com.example.graduation_design.repository.CourseRepository;
import com.example.graduation_design.repository.StudentRepository;
import com.example.graduation_design.repository.selectedCoursesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class courseService {
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
        } else if (selectedCoursesRepository.findByStudentIdAndCourseId(sid, cid)!= null) log.debug("{}", "该学生记录已经存在");
        else log.debug("{}", "学生或者课程信息不存在");
    }

    public void updateCourseStu(int cid,int sid, double score) {
        selectedCourses sc = selectedCoursesRepository.findByStudentIdAndCourseId(sid, cid);
        if(sc!=null){
          sc.setScore(score);
          selectedCoursesRepository.save(sc);
        }
        else log.debug("{}", "该记录不存在");
    }
}