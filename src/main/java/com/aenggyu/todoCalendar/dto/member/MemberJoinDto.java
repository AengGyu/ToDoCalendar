package com.aenggyu.todoCalendar.dto.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 회원 가입 폼에 사용되는 DTO
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinDto {

    @NotEmpty(message = "아이디를 입력해주세요.")
    private String loginId;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;
}
