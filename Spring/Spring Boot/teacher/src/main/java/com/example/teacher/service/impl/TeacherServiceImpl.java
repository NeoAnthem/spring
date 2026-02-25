package com.example.teacher.service.impl;


import com.example.teacher.api_call.StudentApi;
import com.example.teacher.dto.*;

import com.example.teacher.entity.Teacher;
import com.example.teacher.repository.TeacherRepository;
import com.example.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;


    @Autowired
    private StudentApi studentApi;
    @Override
    public StudentDto getById(String rollNostr) {

        return studentApi.getById(rollNostr);
    }

    @Override
    public Teacher register(RegisterDto registerDto) {
        Teacher teacher=new Teacher();
        teacher.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        teacher.setUserName(registerDto.getUserName());
        teacher.setRole(registerDto.getRole());
        teacher.setEmail(registerDto.getEmail());

        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher login(LoginDto loginDto) {
        Teacher teacher= teacherRepository.findByUserName(loginDto.getUserName()).orElseThrow(()-> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(loginDto.getPassword(),teacher.getPassword())){
            throw new RuntimeException("Invalid user");
        }
        return teacher;    }

    @Override
    public List<StudentDto> getallStudent() {
        return studentApi.getallStudent();
    }

    @Override
    public StudentDto updateStudent(String rollnoStr, ReviewDto reviewDto) {
        return studentApi.updateStudent(rollnoStr,reviewDto);
    }

    @Override
    public StudentDto approvedStudent(String rollnoStr, ReviewDto reviewDto) {
        return studentApi.approvedStudent(rollnoStr,reviewDto);
    }

    @Override
    public String deletedStudent(String rollNostr) {
        studentApi.deleteStudent(rollNostr);
        return "Student deleted successfully";
    }

    @Override
    public Resource downloadFile(String rollnoStr) throws IOException {
        return studentApi.downloadInfo(rollnoStr);
    }
}
