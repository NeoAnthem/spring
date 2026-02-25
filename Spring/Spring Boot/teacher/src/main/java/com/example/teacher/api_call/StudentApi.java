package com.example.teacher.api_call;

import com.example.teacher.dto.ReviewDto;
import com.example.teacher.dto.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@FeignClient(name = "STUDENT",path = "/api/v1")
public interface StudentApi {

    @GetMapping("/teacher/{rollNo}")
    StudentDto getById(@PathVariable("rollNo") String rollnoStr);
    @GetMapping("/student")
    List<StudentDto> getallStudent();

    @DeleteMapping("/teacher/{rollNo}")
    ResponseEntity<String> deleteStudent(@PathVariable("rollNo") String rollNostr);

    @GetMapping("/teacher/download/{rollNo}")
    Resource  downloadInfo(@PathVariable("rollNo") String rollnoStr);

    @PutMapping("/teacher/reject/{rollNo}")
    StudentDto updateStudent(@PathVariable("rollNo") String rollnoStr, @RequestBody ReviewDto reviewDto);

    @PutMapping("/teacher/approved/{rollNo}")
    StudentDto approvedStudent(@PathVariable("rollNo") String rollnoStr, @RequestBody ReviewDto reviewDto);
}
