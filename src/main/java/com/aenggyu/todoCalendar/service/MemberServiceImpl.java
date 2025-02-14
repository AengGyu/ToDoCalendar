package com.aenggyu.todoCalendar.service;

import com.aenggyu.todoCalendar.domain.member.Member;
import com.aenggyu.todoCalendar.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member join(Member member) {
        // 중복 회원 검증, 나중에 예외 추가해서 runtime 예외를 직접 만든 예외로 바꿔줄 거임
        // 일단은 runtime ex 터뜨리기
        if (memberRepository.findByLoginId(member.getLoginId()).isPresent()) {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }

        return memberRepository.save(member);
    }

    @Override
    public Member findMemberById(Long id) {
        // Optional 객체 까서 반환하기, 나중에 예외 추가해서 runtime 예외를 직접 만든 예외로 바꿔줄 거임
        // 일단은 runtime ex 터뜨리기
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
    }

    @Override
    public Member findMemberByName(String name) {
        // Optional 객체 까서 반환하기, 나중에 예외 추가해서 runtime 예외를 직접 만든 예외로 바꿔줄 거임
        // 일단은 runtime ex 터뜨리기
        return memberRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}
