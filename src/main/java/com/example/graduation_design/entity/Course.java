package com.example.graduation_design.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String courseName;
    private int lowestScore;//最低分，由导师设置
    private double weight;//权重
    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "course")
    private List<selectedCourses> courses;
}