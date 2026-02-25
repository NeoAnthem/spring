package com.example.student.dto;

import com.example.student.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentFileDto {

    private Long rollNo;

    private MultipartFile file;

}
