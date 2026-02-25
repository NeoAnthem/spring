package com.example.teacher.controller;



import com.example.teacher.dto.*;
import com.example.teacher.entity.Teacher;
import com.example.teacher.service.TeacherService;
import com.example.teacher.service.impl.JwtService;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class TeacherController {


private final JwtService jwtService;

    private final TeacherService teacherService;

    @GetMapping("/teacher/{rollNo}")
        @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public StudentDto getById(@PathVariable("rollNo")String rollnoStr){
        return teacherService.getById(rollnoStr);
    }

    @GetMapping("/student")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public List<StudentDto> getallStudent(){
        List<StudentDto> exitsStudents=teacherService.getallStudent();
        return exitsStudents;
    }


    @PostMapping("/login/teacher")
    public String login(@RequestBody LoginDto loginDto,String role){
        Teacher teacher=teacherService.login(loginDto);
        return jwtService.generateToken(loginDto, String.valueOf(teacher.getRole()));
    }


    @PostMapping("/register/teacher")
    public String register(@RequestBody RegisterDto registerDto){
        Teacher saved= teacherService.register(registerDto);
        return "user register successfully";
    }



    @DeleteMapping("/teacher/{rollNo}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<String> deleteStudent(@PathVariable("rollNo") String rollNostr)
    {
        String studentDto=teacherService.deletedStudent(rollNostr);

        return ResponseEntity.status(200).body(studentDto);
    }
    @GetMapping("/teacher/download/{rollNo}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<Resource>  downloadInfo(@PathVariable("rollNo") String rollnoStr)throws IOException {
        Resource resource=teacherService.downloadFile(rollnoStr);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+resource.getFilename()+"\"").body(resource);

    }

    @PutMapping("/teacher/reject/{rollNo}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("rollNo") String rollnoStr, @RequestBody ReviewDto reviewDto){
        StudentDto student=teacherService.updateStudent(rollnoStr,reviewDto);
        return ResponseEntity.status(200).body(student);
    }
    @PutMapping("/teacher/approved/{rollNo}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<StudentDto> approvedStudent(@PathVariable("rollNo") String rollnoStr, @RequestBody ReviewDto reviewDto) {
        StudentDto student = teacherService.approvedStudent(rollnoStr, reviewDto);
        return ResponseEntity.status(200).body(student);
    }

}
