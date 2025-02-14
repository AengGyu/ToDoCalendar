package com.aenggyu.todoCalendar.service;

import com.aenggyu.todoCalendar.domain.member.Member;

import java.util.List;


public interface MemberService {

    Member join(Member member);
    Member findMemberById(Long id);
    Member findMemberByName(String name);
    List<Member> findAllMembers();
}
