package com.aenggyu.todoCalendar.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 회원 리스트에 필요한 DTO
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberListDto {

    private Long id;
    private String loginId;
    private String name;
}
