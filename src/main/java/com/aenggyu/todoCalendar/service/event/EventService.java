package com.aenggyu.todoCalendar.service.event;

import com.aenggyu.todoCalendar.domain.event.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Event addEvent(Event event);
    List<Event> findEventsByMemberId(Long memberId);
    List<Event> findAllEvents();
    Optional<Event> findEventById(Long id);
    void updateEvent(Event event);
    void deleteEvent(Long id);
}
