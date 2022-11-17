package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.userAdd.UserAddRequest;
import com.mustache.bbs.domain.dto.userAdd.UserAddResponse;
import com.mustache.bbs.domain.dto.userSelectDto.UserSelectRequest;
import com.mustache.bbs.domain.dto.userSelectDto.UserSelectResponse;
import com.mustache.bbs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserSelectResponse> getUser(@PathVariable Long id) {
        UserSelectResponse userSelectResponse = userService.findUser(id);

        return ResponseEntity.ok().body(userSelectResponse);
    }

    @PostMapping("/api/users")
    public ResponseEntity<UserAddResponse> addUser(@RequestBody UserAddRequest userAddRequest) {
        UserAddResponse userAddResponse = userService.addUser(userAddRequest);
        return ResponseEntity.ok().body(userAddResponse);
    }
}
