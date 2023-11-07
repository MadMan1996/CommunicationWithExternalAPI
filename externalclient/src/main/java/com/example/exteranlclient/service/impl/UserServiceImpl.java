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
    private static AtomicInteger count = new AtomicInteger(0);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDto getInfoByLogin(String login) {
        count.getAndIncrement();
        if(count.get()<3) throw new RuntimeException();
        count.set(0);
        User byLogin = userRepository.findUserByLogin(login);
        return userMapper.toUserDto(byLogin);
    }
}
