package com.example.graduation_design.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.id.insert.IdentifierGeneratingInsert;

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
    private int lowestScore;
    private double weight;
    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "course")
    private List<selectedCourses> courses;
}