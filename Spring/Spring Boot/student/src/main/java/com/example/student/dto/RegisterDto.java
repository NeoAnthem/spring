package com.example.student.dto;

import com.example.student.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String userName;
    private String email;
    private String password;
    private Role role;
}
