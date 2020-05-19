package com.example.graduation_design.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class selectedCourses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double score;

    @Column(columnDefinition = "timestamp default current_timestamp",
    insertable = false,
    updatable = false)
    private LocalDateTime insertTime;

    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
}