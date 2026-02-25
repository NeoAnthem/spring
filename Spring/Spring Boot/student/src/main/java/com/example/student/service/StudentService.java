package com.example.student.service;

import com.example.student.dto.*;
import com.example.student.entity.Student;

import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    StudentDto addStudent(@Valid StudentFileDto studentFileDto,String rollNoStr) throws IOException;


    StudentDto getById(String rollNostr);



    StudentDto uploadFile(String rollnoStr, MultipartFile file)throws  IOException;


    Student login(LoginDto loginDto);

    Student register(RegisterDto registerDto);

//    StudentDto updateStudent(String rollnoStr, StudentFileDto studentFileDto);

    List<StudentDto> getallStudent();
    StudentDto updateStudent(String rollnoStr, ReviewDto reviewDto);


    StudentDto approvedStudent(String rollnoStr, ReviewDto reviewDto);

    String deletedStudent(String rollNostr);

    Resource downloadFile(String rollnoStr)throws IOException;
}
