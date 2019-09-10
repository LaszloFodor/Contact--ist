package com.uxstudio.contactlist.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundExceptions() {
        return "User cannot be found! Please use an existing ID!";
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public String handleContactNotFoundExceptions() {
        return "Contact cannot be found! Please use an existing ID!";
    }
}
