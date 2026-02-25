package com.example.student.controller;

import com.example.student.dto.*;
import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import com.example.student.service.impl.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class StudentController {


    private final JwtService jwtService;

    private final StudentService studentService;

    @PutMapping("/student/{rollNo}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentDto> addStudent(@Valid @ModelAttribute StudentFileDto studentFileDto,@PathVariable("rollNo") String rollNostr){
try {

       StudentDto studentDto= studentService.addStudent(studentFileDto,rollNostr);
       return ResponseEntity.status(200).body(studentDto);
} catch (IOException e) {
    System.out.println(e.getMessage());
}
        return ResponseEntity.badRequest().build();
    }



    @GetMapping("/teacher/{rollNo}")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN','STUDENT')")
    public ResponseEntity<StudentDto> getById(@PathVariable("rollNo") String rollNostr){
        StudentDto studentDto=studentService.getById(rollNostr);

        return ResponseEntity.status(200).body(studentDto);
    }


    @PutMapping("/student/resubmit/{rollNo}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentDto> uploadFile(@PathVariable("rollNo") String rollnoStr, @RequestParam MultipartFile file)throws IOException{
        StudentDto studentDto=studentService.uploadFile(rollnoStr,  file);
        return ResponseEntity.status(200).body(studentDto);
    }

    @PostMapping("/login/student")
    public String login(@RequestBody LoginDto loginDto,String role){
        Student student=studentService.login(loginDto);
        return jwtService.generateToken(loginDto, String.valueOf(student.getRole()));
    }


    @PostMapping("/register/student")
    public String register(@RequestBody RegisterDto registerDto){
        Student saved= studentService.register(registerDto);
        return "user register successfully";
    }



    @DeleteMapping("/teacher/{rollNo}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<String> deleteStudent(@PathVariable("rollNo") String rollNostr)
    {
        String studentDto=studentService.deletedStudent(rollNostr);

        return ResponseEntity.status(200).body(studentDto);
    }
    @GetMapping("/teacher/download/{rollNo}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<Resource>  downloadInfo(@PathVariable("rollNo") String rollnoStr)throws IOException {
        Resource resource=studentService.downloadFile(rollnoStr);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+resource.getFilename()+"\"").body(resource);

    }

    @PutMapping("/teacher/reject/{rollNo}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("rollNo") String rollnoStr, @RequestBody ReviewDto reviewDto){
        StudentDto student=studentService.updateStudent(rollnoStr,reviewDto);
        return ResponseEntity.status(200).body(student);
    }
    @PutMapping("/teacher/approved/{rollNo}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<StudentDto> approvedStudent(@PathVariable("rollNo") String rollnoStr, @RequestBody ReviewDto reviewDto) {
        StudentDto student = studentService.approvedStudent(rollnoStr, reviewDto);
        return ResponseEntity.status(200).body(student);
    }


    @GetMapping("/student")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public List<StudentDto> getallStudent(){
        List<StudentDto> exitsStudents=studentService.getallStudent();
        return exitsStudents;
    }

}
