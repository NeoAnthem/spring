package com.example.student.mapper;


import com.example.student.dto.StudentDto;
import com.example.student.dto.StudentFileDto;
import com.example.student.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

//    StudentFileDto mapInfotoDto(Student student);
    Student mapDtotoInfo(StudentFileDto studentFileDto);
    StudentDto mapstudentInfotostudentDto(Student student);
    Student mapstudDtotoInfo(
            StudentDto studentDto);
}
