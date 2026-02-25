package com.example.teacher.service.impl;


import com.example.teacher.config.CustomeUserDetail;
import com.example.teacher.entity.Teacher;
import com.example.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailService implements UserDetailsService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public UserDetails loadUserByUsername(String critdential) throws UsernameNotFoundException {
        Teacher teacher=teacherRepository.findByEmail(critdential)
                .or(()->teacherRepository.findByUserName(critdential))
                .orElseThrow(()->new RuntimeException("User not found"));
        UserDetails userDetails=new CustomeUserDetail(teacher);
        return  userDetails;
    }
}
