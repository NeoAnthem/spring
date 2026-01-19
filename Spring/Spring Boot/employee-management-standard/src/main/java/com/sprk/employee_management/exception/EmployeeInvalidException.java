package com.sprk.employee_management.exception;

import org.springframework.http.HttpStatus;

public class EmployeeInvalidException extends EmployeeException{

    public EmployeeInvalidException(String message, HttpStatus status){
        super(status,message);
    }
}
