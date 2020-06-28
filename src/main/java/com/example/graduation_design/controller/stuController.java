package com.example.graduation_design.controller;

import com.example.graduation_design.component.enableSelect;
import com.example.graduation_design.component.requestComponent;
import com.example.graduation_design.entity.Course;
import com.example.graduation_design.entity.Student;
import com.example.graduation_design.entity.selectedCourses;
import com.example.graduation_design.repository.selectedCoursesRepository;
import com.example.graduation_design.service.CourseService;
import com.example.graduation_design.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stu/")
@Slf4j
public class stuController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private requestComponent requestComponent;
    @Autowired
    private enableSelect enableSelect;


    @GetMapping("index")
    public Map index() {
        Student student = studentService.getStudentById(requestComponent.getUid());
        log.debug("{}", student.getId());
        List<Course> courses= studentService.getCourses(requestComponent.getUid());

        return Map.of("student",student,"selectedCourses",student.getSelectedCourses(),
                "allowSelect",enableSelect.allowSelect());
    }


    @GetMapping("agree")
    public Map agree(){
        Student stu = studentService.getStudentById(requestComponent.getUid());
        stu.setAgree(true);
        studentService.updateStu(stu.getId(), stu);
       return Map.of("student",stu);
    }

}