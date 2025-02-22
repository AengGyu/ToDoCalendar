package com.aenggyu.todoCalendar.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public String handleMemberNotFoundException(MemberNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error/404";
    }

    @ExceptionHandler(UnauthorizedException.class)
    public String handelUnauthorizedException(UnauthorizedException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error/401";
    }

    @ExceptionHandler(EventNotFoundException.class)
    public String handleEventNotFoundException(EventNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error/404";
    }
}
