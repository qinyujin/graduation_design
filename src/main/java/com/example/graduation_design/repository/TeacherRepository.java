package com.example.graduation_design.repository;

import com.example.graduation_design.entity.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends baseRepository<Teacher,Integer> {

    /**
     * 遍历所有教师
     * @return
     */
    @Query("from Teacher t")
    List<Teacher> findAll();


    /**
     * 根据id查找教师
     * @param id
     * @return
     */
    @Query("from Teacher c where c.id=?1")
    Teacher findById(int id);


    /**
     * 添加导师，输入名字，学生数
     * @param name
     * @return
     */
    @Modifying
    @Query(value = "insert into teacher(name,total_students) values(?1,?2)",nativeQuery = true)
    int addTeacher(String name,int stuNum);


    /**
     * 根据id更新导师信息：姓名、学生数
     * @param name
     * @param totalStudents
     * @param suitableStudents
     * @param tid
     * @return
     */
    @Modifying
    @Query(value = "update Teacher c set c.name=?1,c.total_students=?2,c.suitable_students=?3 where c.id=?4",nativeQuery = true)
    int updateTeacherById(String name,int totalStudents,int suitableStudents,int tid);

    /**
     * 根据id删除导师记录
     * @param tno
     * @return
     */
    @Modifying
    @Query("delete Teacher c where c.id=?1")
    int deleteTeacherById(int tno);
}