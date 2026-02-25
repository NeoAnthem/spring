package com.example.teacher.config;


import com.example.teacher.entity.Role;
import com.example.teacher.entity.Teacher;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomeUserDetail implements UserDetails {
private String username;
private String password;
private Collection<? extends  GrantedAuthority>authorities;

public CustomeUserDetail(Teacher student) {
    this.password = student.getPassword();
    this.username = student.getUserName();
    this.authorities =createAuthorite(student.getRole());
}


private Collection<? extends GrantedAuthority>createAuthorite(Role roles){
  return List.of(new SimpleGrantedAuthority("ROLE_"+roles.name()));
}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
