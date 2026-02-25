package com.example.student.exception;

public class EmailAlreadyExits extends RuntimeException {
    public EmailAlreadyExits(String message) {

        super(message);
    }
}
