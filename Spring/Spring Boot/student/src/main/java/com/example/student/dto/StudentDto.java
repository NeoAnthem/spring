package com.example.student.dto;

import com.example.student.entity.Role;
import com.example.student.entity.StatusReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long rollNo;

private String userName;
    private String email;

    private String password;

    private String review;

    private StatusReview status;

    private Role role;
    private String fileName;

    private String filePath;
}
