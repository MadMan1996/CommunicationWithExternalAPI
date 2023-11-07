package com.example.demo.service;

import com.example.demo.dto.UserInfo;

import java.util.concurrent.CompletableFuture;

public interface ExternalUserService {
    CompletableFuture<UserInfo> getActualUserInfo(String login);

    void updateOldUsersInDepartment(Long departmentId);

}
