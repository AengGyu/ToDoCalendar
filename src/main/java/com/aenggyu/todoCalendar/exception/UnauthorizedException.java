package com.aenggyu.todoCalendar.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("접근 권한이 없습니다.");
    }
}
