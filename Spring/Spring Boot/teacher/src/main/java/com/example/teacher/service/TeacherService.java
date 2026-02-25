package com.example.teacher.service;

import com.example.teacher.dto.*;
import com.example.teacher.entity.Teacher;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface TeacherService {

//    List<StudentDto> getallStudent();
    StudentDto getById(String rollNostr);

    Teacher register(RegisterDto registerDto);

    Teacher login(LoginDto loginDto);

    List<StudentDto> getallStudent();

    StudentDto updateStudent(String rollnoStr, ReviewDto reviewDto);


    StudentDto approvedStudent(String rollnoStr, ReviewDto reviewDto);

    String deletedStudent(String rollNostr);

    Resource downloadFile(String rollnoStr)throws IOException;

}
