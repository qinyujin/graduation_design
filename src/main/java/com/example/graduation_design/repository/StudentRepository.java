package com.example.graduation_design.repository;

import com.example.graduation_design.entity.Course;
import com.example.graduation_design.entity.Student;
import com.example.graduation_design.entity.Teacher;
import com.example.graduation_design.entity.directions;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends baseRepository<Student,Integer> {

    /**
     * 查看所有学生
     * @return
     */
    @Query("from Student s")
    List<Student> findAll();

    /**
     * 根据学号id查找学生
     * @param id
     * @return
     */
    @Query("from Student c where c.id=?1")
    Course findById(int id);


    /**
     * 添加学生（需输入学号和姓名，导师先空着）
     * @param sno
     * @param name
     * @return
     */
    @Modifying
    @Query(value = "insert into student(stu_no,name) values(?1,?2)",nativeQuery = true)
    int addStudent(int sno,String name);


    /**
     * 更新学生信息
     * @param name
     * @param teacher
     * @param sno
     * @return
     */
    @Modifying
    @Query("update Student c set c.name=?1,c.teacher=?2 where c.id=?3")
    int updateStudentById(String name,Teacher teacher,int sno);

    /**
     * 根据学号删除学生记录
     * @param sno
     * @return
     */
    @Modifying
    @Query("delete Student c where c.id=?1")
    int deleteStudentById(int sno);
}