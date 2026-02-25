package com.example.student.service.impl;

import com.example.student.config.CustomeUserDetail;
import com.example.student.entity.Role;
import com.example.student.entity.Student;
import com.example.student.repository.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomeUserDetailService implements UserDetailsService {
    @Autowired
    private StudentRespository studentRespository;
    @Override
    public UserDetails loadUserByUsername(String critdential) throws UsernameNotFoundException {
        Student student=studentRespository.findByEmail(critdential)
                .or(()->studentRespository.findByUserName(critdential))
                .orElseThrow(()->new RuntimeException("User not found"));
        UserDetails userDetails=new CustomeUserDetail(student);
        return  userDetails;
    }
}
