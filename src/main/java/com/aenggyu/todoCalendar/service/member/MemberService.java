package com.aenggyu.todoCalendar.service.member;

import com.aenggyu.todoCalendar.domain.member.Member;

import java.util.List;
import java.util.Optional;


public interface MemberService {

    Member join(Member member);
    Member findMemberById(Long id);
    Optional<Member> findMemberByLoginId(String loginId);
    Member findMemberByName(String name);
    List<Member> findAllMembers();
}
