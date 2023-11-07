package com.example.exteranlclient.controller;

import com.example.exteranlclient.dto.UserDto;
import com.example.exteranlclient.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class UserController {
    private final UserService userService;

    @GetMapping("/users/{login}")
    public UserDto getUpdatedInfo(@PathVariable String login) {

        return userService.getInfoByLogin(login);
    }
}
