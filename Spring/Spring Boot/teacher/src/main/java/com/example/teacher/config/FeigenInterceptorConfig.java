package com.example.teacher.config;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.jar.Attributes;

@Configuration
public class FeigenInterceptorConfig {

    @Bean
    public RequestInterceptor requestInterceptor(){
        return resttemplate->{
            ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes!=null){
                HttpServletRequest request=attributes.getRequest();
                String header=request.getHeader("Authorization");
                System.out.println("Authorization Header: " + header);
            if (header!=null){
                resttemplate.header("Authorization",header);
            }
            }
        };
    }
}
