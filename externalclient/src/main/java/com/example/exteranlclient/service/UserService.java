package com.example.exteranlclient.service;

import com.example.exteranlclient.dto.UserDto;

public interface UserService {
    UserDto getInfoByLogin(String login);
}
