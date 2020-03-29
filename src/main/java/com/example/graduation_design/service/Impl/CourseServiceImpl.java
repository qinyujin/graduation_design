package com.example.graduation_design.service.Impl;

import com.example.graduation_design.entity.Course;
import com.example.graduation_design.entity.Teacher;
import com.example.graduation_design.repository.CourseRepository;
import com.example.graduation_design.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    /**
     * 管理员可添加课程
     */
    public void addCourse(String name, int lowestScore, double weight) {
        courseRepository.addCourse(name, lowestScore, weight);
    }

    /**
     * 删除课程(管理员)
     *
     * @param id
     */
    public void deleteCourse(int id) {
        courseRepository.deleteCourseById(id);
    }

    /**
     * 遍历所有课程
     * @return
      */
   public   List<Course> findAll() {
        return courseRepository.findAll();
    }

    /**
     * 按id查看课程
     * @param id
     * @return
     */
    public Course findById(int id){
      return courseRepository.findById(id);
    }

    /**
     * 更改课程信息（管理员）
     * @param lowestScore
     * @param weight
     * @param t
     * @param id
     */
    public void updateCourse(String CourseName,int lowestScore, double weight, Teacher t,int id){
        courseRepository.updateCourseById(CourseName, lowestScore, weight, t, id);
    }

    /**
     * 更新课程信息（导师）
     * @param lowestSocre
     * @param weight
     * @param t
     * @param id
     */
    public void updateCourse(int lowestSocre,double weight,Teacher t,int id){
        courseRepository.updateCourseById(lowestSocre, weight, t, id);
    }

}