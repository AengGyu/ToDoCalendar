package com.aenggyu.todoCalendar.repository.event;

import com.aenggyu.todoCalendar.domain.event.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    Event save(Event event);
    List<Event> findByMemberId(Long memberId);
    List<Event> findAll();
    Optional<Event> findById(Long id);
    void update(Event event);
    void deleteById(Long id);
}
