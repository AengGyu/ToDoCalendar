package com.aenggyu.todoCalendar.interceptor;

import com.aenggyu.todoCalendar.domain.member.Role;
import com.aenggyu.todoCalendar.dto.login.LoginMemberDto;
import com.aenggyu.todoCalendar.exception.UnauthorizedException;
import com.aenggyu.todoCalendar.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("/");
            return false;
        }

        LoginMemberDto loginMember = (LoginMemberDto) session.getAttribute(SessionConst.LOGIN_SESSION);

        if (loginMember.getRole() == null || loginMember.getRole() != Role.ADMIN) {
            throw new UnauthorizedException();
        }

        return true;
    }
}
