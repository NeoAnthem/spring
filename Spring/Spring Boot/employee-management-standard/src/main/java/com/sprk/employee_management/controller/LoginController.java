package com.sprk.employee_management.controller;

import com.sprk.employee_management.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1")
public class LoginController {
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestParam String userName,@RequestParam String password){
        if (userName.equals("admin")&&password.equals("123")) return jwtUtil.generateToken(userName);
        return "Invalid Credentials";
    }

}