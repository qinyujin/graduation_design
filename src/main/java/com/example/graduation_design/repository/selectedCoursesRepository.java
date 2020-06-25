package com.example.graduation_design.repository;

import com.example.graduation_design.entity.Course;
import com.example.graduation_design.entity.selectedCourses;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface selectedCoursesRepository extends baseRepository<selectedCourses,Integer> {
   selectedCourses findByStudentIdAndCourseId(int sid,int cid);

   @Query("select sc.course from selectedCourses sc where sc.student.id=?1")
   List<Course> listByStudentId(int sid);
}