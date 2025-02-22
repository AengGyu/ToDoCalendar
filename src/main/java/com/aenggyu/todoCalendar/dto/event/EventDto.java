package com.aenggyu.todoCalendar.dto.event;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private Long id;

    @NotEmpty(message = "제목을 입력하세요.")
    private String title;

    @NotEmpty(message = "설명을 입력하세요.")
    private String description;

    @NotNull(message = "시작 날짜를 선택하세요.")
    private LocalDate start;

    @NotNull(message = "종료 날짜를 선택하세요.")
    private LocalDate end;

    /**
     * 종료 날짜는 시작 날짜보다 느려야 함
     */
}
