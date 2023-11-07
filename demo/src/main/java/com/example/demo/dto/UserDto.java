package com.example.demo.dto;

import lombok.*;

import java.time.Instant;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private String login;

    private String fullname;
    private String role;

    private String email;

    private Instant birthday;

    private Boolean active;
    private Instant updateDate;
}
