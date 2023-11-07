package com.example.exteranlclient.dto;

import lombok.*;

import java.time.Instant;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String login;
    private String fullName;
    private String phoneNumber;
    private String email;
    private Instant birthDay;
}
