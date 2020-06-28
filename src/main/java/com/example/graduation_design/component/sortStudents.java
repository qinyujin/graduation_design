package com.example.graduation_design.component;

import com.example.graduation_design.entity.Student;
import com.example.graduation_design.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class sortStudents {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> sortAllStuByScore(){
        List<Student> all = studentRepository.findAll();
        for(int i=0;i<all.size();i++){
            for(int j=i+1;j<all.size();j++){
                if(all.get(i).getWeightScore()<all.get(j).getWeightScore()){
                    double temp;

                    temp=all.get(j).getWeightScore();
                    all.get(j).setWeightScore(all.get(i).getWeightScore());
                    all.get(i).setWeightScore(temp);
                }
            }
        }

        return all;
    }
}
