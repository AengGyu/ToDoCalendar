package com.aenggyu.todoCalendar.dto.login;

import com.aenggyu.todoCalendar.domain.member.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 로그인 한 회원의 정보를 넘겨주는 dto, 세션에서 조회
 */
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
