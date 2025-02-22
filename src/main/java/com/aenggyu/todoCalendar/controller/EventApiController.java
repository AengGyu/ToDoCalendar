package com.aenggyu.todoCalendar.controller;

import com.aenggyu.todoCalendar.domain.event.Event;
import com.aenggyu.todoCalendar.dto.event.EventDto;
import com.aenggyu.todoCalendar.dto.login.LoginMemberDto;
import com.aenggyu.todoCalendar.exception.ApiValidationException;
import com.aenggyu.todoCalendar.service.event.EventService;
import com.aenggyu.todoCalendar.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventApiController {

    private final EventService eventService;

    /**
     * 회원별 일정 조회
     */
    @GetMapping("/{memberId}")
    public ResponseEntity<List<EventDto>> getEventsByMember(@PathVariable Long memberId) {
        List<EventDto> events = eventService.findEventsByMemberId(memberId).stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.ok(events);
    }

    /**
     * 모든 일정 조회 (현재 사용 x)
     */
    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<EventDto> events = eventService.findAllEvents().stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.ok(events);
    }

    /**
     * 일정 등록
     */
    @PostMapping
    public ResponseEntity<EventDto> addEvent(@Validated @RequestBody EventDto eventDto,
                                             BindingResult bindingResult,
                                             HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(e -> e.getField(), e -> e.getDefaultMessage()));

            throw new ApiValidationException(errorMap);
        }


        HttpSession session = request.getSession(false);
        LoginMemberDto member = (LoginMemberDto) session.getAttribute(SessionConst.LOGIN_SESSION);

        Event event = eventService.addEvent(new Event(eventDto.getTitle(), eventDto.getDescription(), eventDto.getStart(), eventDto.getEnd(), member.getId()));

        return ResponseEntity.ok(convertToDto(event));
    }

    /**
     * 일정 수정
     */
    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long id,
                                                @Validated @RequestBody EventDto eventDto,
                                                BindingResult bindingResult,
                                                HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(e -> e.getField(), e -> e.getDefaultMessage()));

            throw new ApiValidationException(errorMap);
        }

        HttpSession session = request.getSession(false);
        LoginMemberDto member = (LoginMemberDto) session.getAttribute(SessionConst.LOGIN_SESSION);
        Event event = new Event(id, eventDto.getTitle(), eventDto.getDescription(), eventDto.getStart(), eventDto.getEnd(), member.getId());
        eventService.updateEvent(event);

        return ResponseEntity.ok(eventDto);
    }

    /**
     * 일정 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    private EventDto convertToDto(Event event) {
        return new EventDto(event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getStartDate(),
                event.getEndDate());
    }
}
