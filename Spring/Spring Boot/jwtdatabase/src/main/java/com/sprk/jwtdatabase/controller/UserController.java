package com.sprk.jwtdatabase.controller;

import com.sprk.jwtdatabase.entity.UserEntity;
import com.sprk.jwtdatabase.service.UserService;
import com.sprk.jwtdatabase.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password){

        UserEntity user= userService.authenticate(username,password);

        return jwtUtil.generateToken(user.getUsername(),user.getRole());


    }

}