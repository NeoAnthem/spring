package com.example.teacher.repository;

import com.example.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Optional<Teacher>findByUserName(String username);
    Optional<Teacher> findByEmail(String email);

}
