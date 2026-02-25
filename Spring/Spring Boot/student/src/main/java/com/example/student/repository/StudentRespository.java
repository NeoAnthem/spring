package com.example.student.repository;

import com.example.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRespository extends JpaRepository<Student,Long> {
    Optional<Student>findByUserName(String username);
    Optional<Student> findByEmail(String email);

}
