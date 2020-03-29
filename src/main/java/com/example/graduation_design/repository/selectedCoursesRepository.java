package com.example.graduation_design.repository;

import com.example.graduation_design.entity.selectedCourses;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface selectedCoursesRepository extends baseRepository<selectedCourses,Integer> {

    /**
     * 根据学生id查看学生的选课
     * @param sid
     * @return
     */
    @Query("from selectedCourses sc where sc.student=?1")
    List<selectedCourses> findAllByStuId(int sid);
}