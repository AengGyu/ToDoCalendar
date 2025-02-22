package com.aenggyu.todoCalendar.controller;

import com.aenggyu.todoCalendar.dto.login.LoginMemberDto;
import com.aenggyu.todoCalendar.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home/notLoggedIn";
        }

        LoginMemberDto member = (LoginMemberDto) session.getAttribute(SessionConst.LOGIN_SESSION);
        if (member == null) {
            return "home/notLoggedIn";
        }

        model.addAttribute("member", member);

        return "home/loggedIn";
    }

    @GetMapping("/calendar")
    public String calendarView(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        LoginMemberDto member = (LoginMemberDto) session.getAttribute(SessionConst.LOGIN_SESSION);
        model.addAttribute("member", member);

        return "events/calendar";
    }
}
