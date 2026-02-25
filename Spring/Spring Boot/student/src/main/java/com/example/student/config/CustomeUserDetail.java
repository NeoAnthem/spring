package com.example.student.config;

import com.example.student.entity.Role;
import com.example.student.entity.Student;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomeUserDetail implements UserDetails {
private String username;
private String password;
private Collection<? extends  GrantedAuthority>authorities;

public CustomeUserDetail(Student student) {
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
