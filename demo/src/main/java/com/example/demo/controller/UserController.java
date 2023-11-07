package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.ExternalUserService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final ExternalUserService externalUserService;
    @PostMapping("/users")
    public ResponseEntity<Void> updateOldUsersInDepartment(@RequestParam Long departmentId){
        log.debug("Entering update old users in departmnet endpoint");
        externalUserService.updateOldUsersInDepartment(departmentId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/admins/more10activeInDprtm")
    public List<UserDto> getActiveAdminsGreaterThenTenInDepartment(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        log.debug("Entering getActiveAdminsGreaterThenTenInDepartment endpoint. Page number: {}, page size: {}", pageNumber, pageSize);
        return userService.findActiveAdminsInDepartmentWithMoreThan10ActiveAdmins(pageNumber, pageSize);
    }


}
