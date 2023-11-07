package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserInfo;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Value("${user.data.days.expiration}")
    private Integer daysAgo;

    @Transactional
    public User updateUser(UserInfo actialInfo) {
        log.info("Start updateUser method for {}", actialInfo);
        User userToUpdate = userRepository.findByLogin(actialInfo.getLogin());
        userToUpdate.setEmail(actialInfo.getEmail());
        userToUpdate.setFullname(actialInfo.getFullName());
        userToUpdate.setPhoneNumber(actialInfo.getPhoneNumber());
        userRepository.save(userToUpdate);
        userToUpdate.setUpdateDate(Instant.now());
        log.info("User with login {} was successfully updated", actialInfo.getLogin());
        return userToUpdate;
    }

    public List<UserDto> getUsersWithOldDataByDepartment(Long departmentId) {
        log.info("Start getUsersWithOldDataByDepartment for department with id {}", departmentId);
        Instant dataExpirationDate = Instant.now().minus(daysAgo, ChronoUnit.DAYS);

        List<User> usersWithOldData = userRepository.findUsersByDepartmentUpdatedBefore(departmentId, dataExpirationDate);
        log.info("Recived users need to be updated: {}", usersWithOldData);
        return usersWithOldData.stream()
                .map(userMapper::toUserDto).collect(Collectors.toList());

    }


    public List<UserDto> findActiveAdminsInDepartmentWithMoreThan10ActiveAdmins(Integer pageNumber, Integer pageSize) {
        log.info("Start findActiveAdminsInDepartmentWithMoreThan10ActiveAdmins method. Page number: {}, page size: {}",
                pageNumber, pageSize);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<User> activeAdmins = userRepository.findActiveAdminsInDepartmentWithMoreThanCountActiveAdmin(10L, pageable);
        log.info("Admins meet the criteria: {}", activeAdmins);
        return activeAdmins.stream()
                .map(userMapper::toUserDto).collect(Collectors.toList());
    }

}
