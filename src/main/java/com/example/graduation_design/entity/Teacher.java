package com.example.graduation_design.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    @MapsId
    private User user;

    private int totalStudents;//学生数

    private int SuitableStudents;//符合加权学生范围

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;
    @OneToMany(mappedBy = "teacher")
    private List<Student> students;
}