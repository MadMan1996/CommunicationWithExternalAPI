package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserInfo;
import com.example.demo.service.ExternalUserService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalUserServiceImpl implements ExternalUserService {
    @Value("${external.service.url.user-service}")
    private String serviceBaseUrl;
    private final UserService userService;
    private final RestTemplate userServiceClient;
    private final RetryTemplate retryTemplate;
    @Lazy
    @Autowired
    private ExternalUserService externalUserService;

    @Async
    //желательно добавить Circuit breaker, чтобы не было деградации,
    // если внешний клиент не доступен длительное время.
    public CompletableFuture<UserInfo> getActualUserInfo(String login) {
        log.info("Start getActualUserInfo method for {}", login);
        String serviceUrl = String.format("%s%s", serviceBaseUrl, login);
        UserInfo actualUserInfo = retryTemplate.execute(context -> {
            log.trace("Calling externalUserService {} for {}", serviceUrl, login);
            return userServiceClient.getForObject(serviceUrl, UserInfo.class);
        }, context -> {
            throw new RestClientException(String.format("Unable get actual user info for %s. Service %s is unavailable", login, serviceUrl));
        });
        log.info("Actual user info received: {}", actualUserInfo);
        return CompletableFuture.completedFuture(actualUserInfo);
    }


    public void updateOldUsersInDepartment(Long departmentId) {
        log.info("Start updateOldUsersInDepartment method for department id: {}", departmentId);
        List<UserDto> usersWithOldData = userService.getUsersWithOldDataByDepartment(departmentId);
        List<CompletableFuture<UserInfo>> actualUsersInfo = fetchActualUsersData(usersWithOldData);


        log.debug("Updating old users data");
        for (CompletableFuture<UserInfo> info : actualUsersInfo) {
            userService.updateUser(info.join());
        }
        log.info("Users with old data in department with id {} were successfully updated", departmentId);
    }

    private List<CompletableFuture<UserInfo>> fetchActualUsersData(List<UserDto> usersWithOldData) {
        log.debug("Fetching actual info for users with old data");
        List<CompletableFuture<UserInfo>> actualUsersInfo = new ArrayList<>();
        for (var user : usersWithOldData) {
            actualUsersInfo.add(externalUserService.getActualUserInfo(user.getLogin()));

        }
        return actualUsersInfo;
    }

}
