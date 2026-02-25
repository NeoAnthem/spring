package com.example.teacher.dto;

import com.example.teacher.entity.Role;
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
