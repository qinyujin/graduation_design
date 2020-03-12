package com.example.graduation_design.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class expectionDirection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private directions direction;
}