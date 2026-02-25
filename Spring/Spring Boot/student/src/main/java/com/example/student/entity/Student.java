package com.example.student.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rollNo;

    private String userName;


    private String email;

    private String password;



    private String fileName;

    private String filePath;


    private String review;

    @Enumerated(EnumType.STRING)
    private StatusReview status;

    @Enumerated(EnumType.STRING)
    private Role role;
}
