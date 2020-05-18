package com.example.graduation_design.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Student {
    @Id
    private int id;
    @OneToOne
    @MapsId
    private User user;

    private double weightScore;//学生加权分数

    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "student")
    private List<selectedCourses> courses;
}