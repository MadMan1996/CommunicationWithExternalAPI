package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.example.demo.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(expression ="java( String.valueOf(user.getRole().getName()))", target = "role")
    UserDto toUserDto(User user);
}
