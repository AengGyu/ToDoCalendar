package com.aenggyu.todoCalendar.dto.event;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class EventListDto {

    private Long id;

    private String title;

    private String description;

    private LocalDate start;

    private LocalDate end;

    private String name;
}
