package com.example.student.service.impl;



import com.example.student.dto.*;
import com.example.student.entity.StatusReview;
import com.example.student.entity.Student;
import com.example.student.mapper.StudentMapper;
import com.example.student.repository.StudentRespository;
import com.example.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRespository studentRespository;
    private final StudentMapper studentMapper;
    @Value("${file.upload-dir}")
    private String uploadDirectory;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public StudentDto addStudent(StudentFileDto studentFileDto,String rollNostr) throws IOException {
        if (!Pattern.matches("^\\d+$", rollNostr)) {
            throw new RuntimeException("Student with id Not found");
        }
        Long rollno = Long.parseLong(rollNostr);
        Student student = studentRespository.findById(rollno).orElseThrow(() -> new RuntimeException("Student Not found"));

        String filename = studentFileDto.getFile().getOriginalFilename();


        File dir = new File(uploadDirectory);
        File destination = new File(dir, filename);
        studentFileDto.getFile().transferTo(destination);
        student.setFileName(filename);
        student.setReview("Under Review");
        student.setStatus(StatusReview.SUBMITTED);
        student.setFilePath(uploadDirectory + "/" + filename);
        Student savedStudent = studentRespository.save(student);
        StudentDto studentDto = studentMapper.mapstudentInfotostudentDto(savedStudent);
        return studentDto;

    }



    @Override
    public StudentDto getById(String rollNostr) {
        if (!Pattern.matches("^\\d+$", rollNostr)) {
            throw new RuntimeException("Student with id Not found");
        }
        Long rollno = Long.parseLong(rollNostr);
        Student student = studentRespository.findById(rollno).orElseThrow(() -> new RuntimeException("Student Not found"));

        return studentMapper.mapstudentInfotostudentDto(student);
    }


    @Override
    public StudentDto uploadFile(String rollnoStr, MultipartFile file) throws IOException{
        if (!Pattern.matches("^\\d+$", rollnoStr)) {
            throw new RuntimeException("Student with id Not found");
        }
        Long rollno=Long.parseLong(rollnoStr);
        Student student= studentRespository.findById(rollno).orElseThrow(()-> new RuntimeException("Student Not found"));
        student.setFileName(file.getOriginalFilename());
        student.setFilePath(uploadDirectory + "/" + file.getOriginalFilename());
        File dir = new File(uploadDirectory);
        File destination = new File(dir, file.getOriginalFilename());
        file.transferTo(destination);
        student.setStatus(StatusReview.RESUBMITTED);
        student.setReview("Re-submitted assigenement");
        studentRespository.save(student);
        return studentMapper.mapstudentInfotostudentDto(student);
    }



    @Override
    public Student login(LoginDto loginDto) {
        Student student= studentRespository.findByUserName(loginDto.getUserName()).orElseThrow(()-> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(loginDto.getPassword(),student.getPassword())){
            throw new RuntimeException("Invalid user");
        }
        return student;
    }

    @Override
    public Student register(RegisterDto registerDto) {
        Student student=new Student();
        student.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        student.setUserName(registerDto.getUserName());
        student.setRole(registerDto.getRole());
        student.setEmail(registerDto.getEmail());

        return studentRespository.save(student);
    }


    @Override
    public List<StudentDto> getallStudent() {
        List<Student> student = studentRespository.findAll();
        return student.stream().map(studentMapper::mapstudentInfotostudentDto).collect(Collectors.toList());
    }
    @Override
    public String deletedStudent(String rollNostr) {
        if (!Pattern.matches("^\\d+$", rollNostr)) {
            throw new RuntimeException("Student with id Not found");
        }
        Long rollno = Long.parseLong(rollNostr);
        Student student = studentRespository.findById(rollno).orElseThrow(() -> new RuntimeException("Student Not found"));
        studentRespository.delete(student);
        return "Student deleted successfully";

    }

    @Override
    public Resource downloadFile(String rollnoStr) throws IOException {

        if (!Pattern.matches("^\\d+$", rollnoStr)) {
            throw new RuntimeException("Student with id Not found");
        }
        Long rollno = Long.parseLong(rollnoStr);
        Student student = studentRespository.findById(rollno).orElseThrow(() -> new RuntimeException("Student Not found"));

        Path path = Paths.get(uploadDirectory, student.getFileName());
        Resource resource = new UrlResource(path.toUri());
        if (!resource.exists()) {
            ResponseEntity.notFound().build();
        }

        return resource;
    }

    @Override
    public StudentDto updateStudent(String rollnoStr, ReviewDto reviewDto) {
        if (!Pattern.matches("^\\d+$", rollnoStr)) {
            throw new RuntimeException("Student with id Not found");
        }
        Long rollno=Long.parseLong(rollnoStr);
        Student student= studentRespository.findById(rollno).orElseThrow(()-> new RuntimeException("Student Not found"));
        student.setReview(reviewDto.getReview());
        student.setStatus(StatusReview.REJECTED);
        studentRespository.save(student);
        return studentMapper.mapstudentInfotostudentDto(student);
    }



    @Override
    public StudentDto approvedStudent(String rollnoStr, ReviewDto reviewDto) {
        if (!Pattern.matches("^\\d+$", rollnoStr)) {
            throw new RuntimeException("Student with id Not found");
        }
        Long rollno=Long.parseLong(rollnoStr);
        Student student= studentRespository.findById(rollno).orElseThrow(()-> new RuntimeException("Student Not found"));
        student.setReview(reviewDto.getReview());
        student.setStatus(StatusReview.APPROVED);
        studentRespository.save(student);
        return studentMapper.mapstudentInfotostudentDto(student);
    }
}



