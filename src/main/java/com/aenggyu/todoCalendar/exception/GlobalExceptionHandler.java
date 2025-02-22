package com.aenggyu.todoCalendar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(ApiValidationException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleApiValidationException(ApiValidationException e) {
        Map<String, String> response = new HashMap<>(e.getErrorMap());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(EventNotFoundException.class) // 현재 사용 x
    public String handleEventNotFoundException(EventNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error/404";
    }
}
