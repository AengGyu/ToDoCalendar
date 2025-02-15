package com.aenggyu.todoCalendar.service.login;

import com.aenggyu.todoCalendar.domain.member.Member;
import com.aenggyu.todoCalendar.dto.login.LoginDto;
import com.aenggyu.todoCalendar.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final MemberService memberService;

    @Override
    public Optional<Member> login(LoginDto loginDto) {
        Optional<Member> member = memberService.findMemberByLoginId(loginDto.getLoginId());

        if (member.isEmpty() || !member.get().getPassword().equals(loginDto.getPassword())) {
            return Optional.empty();
        }

        return member;
    }
}
