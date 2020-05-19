package com.example.graduation_design.repository;

import com.example.graduation_design.entity.selectedCourses;

public interface selectedCoursesRepository extends baseRepository<selectedCourses,Integer> {
   selectedCourses findByStudentIdAndCourseId(int sid,int cid);
}