package com.aenggyu.todoCalendar.dto.member;

import com.aenggyu.todoCalendar.domain.member.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 각 회원 조회에 필요한 DTO
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private String loginId;
    private String name;
    private Role role;
}
