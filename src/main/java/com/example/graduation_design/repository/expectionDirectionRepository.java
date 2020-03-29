package com.example.graduation_design.repository;

import com.example.graduation_design.entity.directions;
import com.example.graduation_design.entity.expectionDirection;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface expectionDirectionRepository extends baseRepository<expectionDirection,Integer> {

    /**
     * 根据学生学号查询学生的方向
     * @param sid
     * @return
     */
    @Query("from expectionDirection ed where ed.student=?1")
    List<directions> findByStuId(int sid);
}