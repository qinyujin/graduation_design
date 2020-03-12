package com.example.graduation_design.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class directions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String detail;
    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "direction")
    private List<expectionDirection> directions;
}