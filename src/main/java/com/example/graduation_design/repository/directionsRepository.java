package com.example.graduation_design.repository;

import com.example.graduation_design.entity.Course;
import com.example.graduation_design.entity.Teacher;
import com.example.graduation_design.entity.directions;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface directionsRepository extends baseRepository<directions,Integer> {

    /**
     * 查看所有方向
     * @return
     */
    @Query("from directions d")
    List<directions> findAll();

    /**
     * 根据方向id查找方向
     * @param id
     * @return
     */
    @Query("from directions c where c.id=?1")
    directions findById(int id);


    /**
     * 添加方向(输入Detail和导师id)
     * @param detail
     * @param tid
     * @return
     */
    @Modifying
    @Query(value = "insert into directions(detail,teacher_id) values(?1,?2)",nativeQuery = true)
    int addDirections(String detail,int tid);

    /**
     * 添加方向(只输入Detail)
     * @param detail
     * @return
     */
    @Modifying
    @Query(value = "insert into directions(detail) values(?1)",nativeQuery = true)
    int addDirections(String detail);


    /**
     * 根据方向id修改方向信息，可修改Detail和导师信息
     * @param detail
     * @param t
     * @param did
     * @return
     */
    @Modifying
    @Query("update directions c set c.detail=?1,c.teacher=?2 where c.id=?3")
    int updateDirectionsById(String detail, Teacher t, int did);


    @Modifying
    @Query("update directions c set c.teacher=?1 where c.id=?2")
    int updateDirectionsById( Teacher t, int did);

    /**
     * 根据方向id删除方向
     * @param id
     * @return
     */
    @Modifying
    @Query("delete directions c where c.id=?1")
    int deleteDirectionsById(int id);
}