package com.example.graduation_design.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.usertype.LoggableUserType;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int totalStudents;
    private int SuitableStudents;
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;
    @OneToMany(mappedBy = "teacher")
    private List<Student> students;
    @OneToMany(mappedBy = "teacher")
    private List<directions> directions;
}