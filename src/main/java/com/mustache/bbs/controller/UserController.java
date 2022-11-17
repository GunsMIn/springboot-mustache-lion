package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.userSelectDto.UserSelectRequest;
import com.mustache.bbs.domain.dto.userSelectDto.UserSelectResponse;
import com.mustache.bbs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<UserSelectResponse> getUser(@RequestBody UserSelectRequest userSelectRequest) {
        UserSelectResponse userSelectResponse = userService.findUser(userSelectRequest);

        return ResponseEntity.ok().body(userSelectResponse);
    }
}
