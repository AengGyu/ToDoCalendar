package com.aenggyu.todoCalendar.controller;

import com.aenggyu.todoCalendar.domain.member.Member;
import com.aenggyu.todoCalendar.dto.login.LoginDto;
import com.aenggyu.todoCalendar.dto.login.LoginMemberDto;
import com.aenggyu.todoCalendar.service.login.LoginService;
import com.aenggyu.todoCalendar.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new LoginDto());
        return "login/login";
    }

    @PostMapping("/login")
    public String loginForm(@Validated @ModelAttribute("login") LoginDto loginDto,
                            BindingResult bindingResult,
                            @RequestParam(defaultValue = "/") String redirectURL,
                            HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/login";
        }

        Optional<Member> optionalMember = loginService.login(loginDto);
        if (optionalMember.isEmpty()) {
            bindingResult.reject("login fail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login/login";
        }

        Member member = optionalMember.get();
        LoginMemberDto loginMemberDto = getLoginMemberDto(member);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_SESSION, loginMemberDto);

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    private static LoginMemberDto getLoginMemberDto(Member member) {
        return new LoginMemberDto(member.getId(), member.getLoginId(), member.getName(), member.getRole());
    }
}
