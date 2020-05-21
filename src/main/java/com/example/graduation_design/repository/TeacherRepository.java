package com.example.graduation_design.repository;

import com.example.graduation_design.entity.Teacher;
public interface TeacherRepository extends baseRepository<Teacher,Integer> {

    Teacher findByUser_Num(String num);
}