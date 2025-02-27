package com.aenggyu.todoCalendar.controller;

import com.aenggyu.todoCalendar.domain.member.Member;
import com.aenggyu.todoCalendar.domain.member.Role;
import com.aenggyu.todoCalendar.dto.member.MemberJoinDto;
import com.aenggyu.todoCalendar.dto.member.MemberDto;
import com.aenggyu.todoCalendar.dto.member.MemberListDto;
import com.aenggyu.todoCalendar.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // 특정 회원 조회
    @GetMapping("/{memberId}")
    public String member(@PathVariable Long memberId, Model model) {
        Member member = memberService.findMemberById(memberId);
        MemberDto memberDto = getMemberResponseDto(member);
        model.addAttribute("member", memberDto);

        return "members/member";
    }

    // 전체 회원 조회
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberService.findAllMembers();

        List<MemberListDto> membersList = members.stream().
                map(m -> new MemberListDto(m.getId(), m.getLoginId(), m.getName())).
                toList();
        model.addAttribute("members", membersList);

        return "members/members";
    }

    // 회원 가입 폼으로 이동
    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("member", new MemberJoinDto());
        return "members/join";
    }

    // 회원 가입
    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("member") MemberJoinDto memberJoinDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "members/join";
        }

        if (memberService.findMemberByLoginId(memberJoinDto.getLoginId()).isPresent()) {
            bindingResult.reject("exists loginID", "이미 존재하는 아이디입니다.");
            return "members/join";
        }

        Member member = new Member(memberJoinDto.getLoginId(), memberJoinDto.getPassword(), memberJoinDto.getName(), Role.USER);
        Member joinedMember = memberService.join(member);

        redirectAttributes.addAttribute("memberId", member.getId());

        return "redirect:/members/welcome/{memberId}";
    }

    // 회원 가입 성공 시 이동
    @GetMapping("/welcome/{memberId}")
    public String welcome(@PathVariable Long memberId, Model model) {
        Member member = memberService.findMemberById(memberId);
        MemberDto memberDto = getMemberResponseDto(member);
        model.addAttribute("member", memberDto);
        return "members/welcome";
    }

    private MemberDto getMemberResponseDto(Member member) {
        return new MemberDto(member.getLoginId(), member.getName(), member.getRole());
    }
}
