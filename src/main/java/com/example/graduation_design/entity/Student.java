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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stuNo;
    private String name;
    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "student")
    private List<expectionDirection> directions;
    @OneToMany(mappedBy = "student")
    private List<selectedCourses> courses;
}