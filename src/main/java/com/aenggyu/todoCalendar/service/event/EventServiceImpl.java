package com.aenggyu.todoCalendar.service.event;

import com.aenggyu.todoCalendar.domain.event.Event;
import com.aenggyu.todoCalendar.exception.InvalidDateException;
import com.aenggyu.todoCalendar.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    @Override
    public Event addEvent(Event event) {
        log.info("validateDate at addEvent()");
        validateDate(event);
        return eventRepository.save(event);
    }

    @Override
    public List<Event> findEventsByMemberId(Long memberId) {
        return eventRepository.findByMemberId(memberId);
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void updateEvent(Event event) {
        log.info("validateDate at updateEvent()");
        validateDate(event);
        eventRepository.update(event);
    }

    @Override
    public Optional<Event> findEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    private void validateDate(Event event) {
        if (event.getStartDate().isAfter(event.getEndDate())) {
            throw new InvalidDateException("종료 날짜는 시작 날짜보다 늦어야 합니다.");
        }
    }
}
