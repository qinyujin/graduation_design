package com.example.graduation_design.component;

import com.example.graduation_design.repository.CourseRepository;
import com.example.graduation_design.repository.StudentRepository;
import com.example.graduation_design.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class updateSuitable {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseRepository courseRepository;

    private boolean suit=true;
    private double sum=0.0;
    private double aim=0.0;
    private int amount=0;

    public void update(){
        aim=0.0;
        amount=0;
        //在所有学生中找到各科分数均大于合格线的,并且更新教师的合适学生数,由于只有王波老师使用，就简单实现
        //如何判断合格：简单判断法：每一门课程的最低分加上权重得到最终的一个成绩值，只有学生的平均加权成绩大于等于这个值才算合格.

        //计算aim
        courseRepository.findAll().forEach(c ->{
            log.debug("{}", c.getWeight()+" "+c.getLowestScore()+" "+c.getWeight()*c.getLowestScore());
            aim+=c.getWeight()*c.getLowestScore();
            log.debug("{}", aim);
        });

        studentRepository.findAll().forEach(
                s -> {
                    sum=0;
                    suit=true;


                    s.getSelectedCourses().forEach(
                            sc ->{
                                sum+=sc.getScore()*sc.getCourse().getWeight();//每一门课程加权分数加起来，如果合格则可以
                            }
                    );

                    log.debug("{}",s.getId()+":"+sum);
                    if(sum<aim)suit=false;

                    if(suit){//如果符合条件
                        amount++;
                    }

                }

        );

        teacherService.updateSuitableStus(amount);
    }
}