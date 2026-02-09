package com.sprk.jwtdatabase.service;

import com.sprk.jwtdatabase.entity.UserEntity;
import com.sprk.jwtdatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity authenticate(String username,String password){
        UserEntity user=userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User Not found"));
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new RuntimeException("Invalid user");
        }
        return user;
    }
}