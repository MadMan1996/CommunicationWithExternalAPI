package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserInfo;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    User updateUser(UserInfo actualInfo);

    List<UserDto> getUsersWithOldDataByDepartment(Long departmentId);

    List<UserDto> findActiveAdminsInDepartmentWithMoreThan10ActiveAdmins(Integer pageNumber, Integer pageSize);
}
