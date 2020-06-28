package com.example.graduation_design.component;

import com.example.graduation_design.entity.Teacher;
import com.example.graduation_design.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class enableSelect {
    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * 判断教师的可选学生数和已选学生数
     */
    public boolean allowSelect() {
        Teacher t = teacherRepository.findById(1).orElse(null);

        if(t.getStudents().size()==t.getTotalStudents())
        return false;
        else return true;
    }
}