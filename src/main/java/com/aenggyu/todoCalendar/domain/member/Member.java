package com.aenggyu.todoCalendar.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private Long id;
    private String loginId;
    private String password;
    private String name;
    private Role role;

    public Member(String loginId, String password, String name, Role role) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = role;
    }
}
