package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserInfo {
    private String login;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String birthDay;

}
