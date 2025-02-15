package com.aenggyu.todoCalendar.dto.login;

import com.aenggyu.todoCalendar.domain.member.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginMemberDto {

    private Long id;
    private String loginId;
    private String name;
    private Role role;
}
