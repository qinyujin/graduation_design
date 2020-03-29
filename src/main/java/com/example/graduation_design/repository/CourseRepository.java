package com.example.graduation_design.repository;

import com.example.graduation_design.entity.Course;
import com.example.graduation_design.entity.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends baseRepository<Course,Integer> {
    /**
     * 根据课程id查找课
     * @param id
     * @return
     */
    @Query("from Course c where c.id=?1")
    Course findById(int id);

    /**
     * 查询所有课程
     * @return
     */
     @Query("from Course c")
    List<Course> findAll();

    /**
     * 添加课程
     * @param name
     * @param lowestScore
     * @param weight
     * @return
     */
     @Modifying
     @Query(value = "insert into Course(course_name,lowest_score,weight) values(?1,?2,?3)",nativeQuery = true)
     int addCourse(String name,int lowestScore,double weight);


    /**
     * 根据课程id修改课程信息
     * @param lowestScore
     * @param weight
     * @param id
     * @return
     */
     @Modifying
     @Query("update Course c set c.courseName=?1,c.lowestScore=?2,c.weight=?3,c.teacher=?4 where c.id=?5")
     int updateCourseById(String CourseName, int lowestScore, double weight, Teacher t,int id);

    @Modifying
    @Query("update Course c set c.lowestScore=?1,c.weight=?2,c.teacher=?3 where c.id=?4")
    int updateCourseById( int lowestScore, double weight, Teacher t,int id);


    @Modifying
    @Query("update Course c set c.courseName=?1 where c.id=?2")
    int updateCourseById(String CourseName,int id);

    /**
     * 根据课程id删除课程
     * @param id
     * @return
     */
     @Modifying
     @Query("delete Course c where c.id=?1")
     int deleteCourseById(int id);
}