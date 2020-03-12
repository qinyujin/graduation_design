package com.example.graduation_design.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class selectedCourses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int score;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
}