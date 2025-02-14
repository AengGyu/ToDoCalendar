package com.aenggyu.todoCalendar.controller;

import com.aenggyu.todoCalendar.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        // 로그인 안 한 사용자
        if (session == null || session.getAttribute(SessionConst.LOGIN_SESSION) == null) {
            return "home/notLoggedIn";
        }

        return "home/loggedIn";
    }
}
