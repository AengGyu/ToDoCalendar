package com.aenggyu.todoCalendar.service.member;

import com.aenggyu.todoCalendar.domain.member.Member;
import com.aenggyu.todoCalendar.exception.MemberNotFoundException;
import com.aenggyu.todoCalendar.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member join(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException());
    }

    @Override
    public Optional<Member> findMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    @Override
    public Member findMemberByName(String name) {
        return memberRepository.findByName(name)
                .orElseThrow(() -> new MemberNotFoundException());
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}
