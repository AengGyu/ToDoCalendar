package com.aenggyu.todoCalendar.service.login;

import com.aenggyu.todoCalendar.domain.member.Member;
import com.aenggyu.todoCalendar.dto.login.LoginDto;

import java.util.Optional;

public interface LoginService {

    Optional<Member> login(LoginDto loginDto);
}
