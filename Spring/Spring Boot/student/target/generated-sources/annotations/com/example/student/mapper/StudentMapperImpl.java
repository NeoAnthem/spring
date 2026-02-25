package com.example.student.mapper;

import com.example.student.dto.StudentDto;
import com.example.student.dto.StudentFileDto;
import com.example.student.entity.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-22T17:34:11+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Student mapDtotoInfo(StudentFileDto studentFileDto) {
        if ( studentFileDto == null ) {
            return null;
        }

        Student student = new Student();

        student.setRollNo( studentFileDto.getRollNo() );

        return student;
    }

    @Override
    public StudentDto mapstudentInfotostudentDto(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDto.StudentDtoBuilder studentDto = StudentDto.builder();

        studentDto.rollNo( student.getRollNo() );
        studentDto.userName( student.getUserName() );
        studentDto.email( student.getEmail() );
        studentDto.password( student.getPassword() );
        studentDto.review( student.getReview() );
        studentDto.status( student.getStatus() );
        studentDto.role( student.getRole() );
        studentDto.fileName( student.getFileName() );
        studentDto.filePath( student.getFilePath() );

        return studentDto.build();
    }

    @Override
    public Student mapstudDtotoInfo(StudentDto studentDto) {
        if ( studentDto == null ) {
            return null;
        }

        Student student = new Student();

        student.setRollNo( studentDto.getRollNo() );
        student.setUserName( studentDto.getUserName() );
        student.setEmail( studentDto.getEmail() );
        student.setPassword( studentDto.getPassword() );
        student.setFileName( studentDto.getFileName() );
        student.setFilePath( studentDto.getFilePath() );
        student.setReview( studentDto.getReview() );
        student.setStatus( studentDto.getStatus() );
        student.setRole( studentDto.getRole() );

        return student;
    }
}
