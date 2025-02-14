package com.aenggyu.todoCalendar.controller;

import com.aenggyu.todoCalendar.domain.member.Member;
import com.aenggyu.todoCalendar.dto.member.MemberDto;
import com.aenggyu.todoCalendar.service.MemberService;
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

    @GetMapping("/{memberId}")
    public String member(@PathVariable Long memberId, Model model) {
        Member member = memberService.findMemberById(memberId);
        model.addAttribute("member", member);

        return "members/member";
    }

    @GetMapping
    public String members(Model model) {
        List<Member> members = memberService.findAllMembers();
        model.addAttribute("members", members);

        return "members/members";
    }

    @GetMapping("/join")
    public String join(Model model) {
        // thymeleaf에서 th:object=${member} 를 사용해야 돼서 빈 dto 객체를 모델로 전달해야함
        model.addAttribute("member", new MemberDto());
        return "members/join";
    }

    @PostMapping("/join")
    public String joinForm(@Validated @ModelAttribute("member") MemberDto memberDto,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "members/join";
        }

        // 아이디 중복 검사는 join 할 때 memberService 에서 처리

        Member member = new Member(memberDto.getLoginId(), memberDto.getPassword(), memberDto.getName());
        Member joinedMember = memberService.join(member);

        redirectAttributes.addAttribute("memberId", member.getId());
        redirectAttributes.addAttribute("isNew", true);

        return "redirect:/members/{memberId}";
    }
}
