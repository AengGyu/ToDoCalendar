package com.aenggyu.todoCalendar.exception;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException() {
        super("일정 정보를 찾을 수 없습니다.");
    }
}
