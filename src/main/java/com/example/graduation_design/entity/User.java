package com.example.graduation_design.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public enum Role {
        STUDENT, TEACHER, ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @NotBlank(message = "用户名不能为空")
    @Size(min = 6,message = "用户名长度不小于{min}")
    private String num;//学号or工号

    private String name;

    @Size(min = 6,message = "用户密码长度不小于{min}")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Role role;

    @Column(columnDefinition = "timestamp default current_timestamp",
            updatable = false,
            insertable = false
    )
    private LocalDateTime insertTime;
}