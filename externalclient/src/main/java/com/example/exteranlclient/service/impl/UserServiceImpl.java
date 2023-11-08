package com.example.exteranlclient.service.impl;

import com.example.exteranlclient.dto.UserDto;
import com.example.exteranlclient.entity.User;
import com.example.exteranlclient.mapper.UserMapper;
import com.example.exteranlclient.repository.UserRepository;
import com.example.exteranlclient.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDto getInfoByLogin(String login) {
        User byLogin = userRepository.findUserByLogin(login);
        return userMapper.toUserDto(byLogin);
    }
}
