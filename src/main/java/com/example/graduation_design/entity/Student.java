package com.example.graduation_design.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"directionsList","selectedCourses"})
public class Student {
    @Id
    private int id;
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    @MapsId
    private User user;

    private double weightScore;//学生加权分数

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "student")
    private List<directions> directionsList;
    @OneToMany(mappedBy = "student",fetch = FetchType.EAGER)
    private List<selectedCourses> selectedCourses;
}