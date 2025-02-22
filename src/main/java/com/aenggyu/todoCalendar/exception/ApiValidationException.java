package com.aenggyu.todoCalendar.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class ApiValidationException extends RuntimeException {

    private final Map<String, String> errorMap;

    public ApiValidationException(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }
}
