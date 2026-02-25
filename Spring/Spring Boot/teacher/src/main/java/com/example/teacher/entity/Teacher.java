package com.example.teacher.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tId;

    private String userName;


    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
