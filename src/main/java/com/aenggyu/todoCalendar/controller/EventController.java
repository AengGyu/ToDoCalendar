package com.aenggyu.todoCalendar.controller;

import com.aenggyu.todoCalendar.domain.event.Event;
import com.aenggyu.todoCalendar.dto.event.EventListDto;
import com.aenggyu.todoCalendar.dto.login.LoginMemberDto;
import com.aenggyu.todoCalendar.service.event.EventService;
import com.aenggyu.todoCalendar.service.member.MemberService;
import com.aenggyu.todoCalendar.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;
    private final MemberService memberService;

    @GetMapping("/calendar")
    public String calendarView(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        LoginMemberDto member = (LoginMemberDto) session.getAttribute(SessionConst.LOGIN_SESSION);
        model.addAttribute("member", member);

        return "events/calendar";
    }

    @GetMapping("/all")
    public String getAllEvent(Model model) {
        List<EventListDto> events = eventService.findAllEvents().stream()
                .map(e -> convertToDto(e))
                .toList();
        model.addAttribute("events", events);

        return "events/events";
    }

    private EventListDto convertToDto(Event event) {
        return new EventListDto(event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getStartDate(),
                event.getEndDate(),
                memberService.findMemberById(event.getMemberId()).getName());
    }
}
