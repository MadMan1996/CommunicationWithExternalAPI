package com.example.exteranlclient.mapper;

import com.example.exteranlclient.entity.User;
import com.example.exteranlclient.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
}
