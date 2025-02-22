package com.aenggyu.todoCalendar;

import com.aenggyu.todoCalendar.domain.member.Member;
import com.aenggyu.todoCalendar.domain.member.Role;
import com.aenggyu.todoCalendar.service.member.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberService memberService;

    @PostConstruct
    public void init() {
        Member admin = new Member("admin", "admin!", "admin", Role.ADMIN);
        Member test = new Member("test", "test!", "test", Role.USER);

        memberService.join(admin);
        memberService.join(test);
    }
}
