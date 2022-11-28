package com.mustache.bbs.controller;

import com.mustache.bbs.domain.Response;
import com.mustache.bbs.domain.user.UserDto;
import com.mustache.bbs.domain.user.UserJoinRequest;
import com.mustache.bbs.domain.user.UserJoinResponse;
import com.mustache.bbs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest) {
        UserDto userDto = userService.join(userJoinRequest);
        return Response.success(new UserJoinResponse(userDto.getUserName(), userDto.getPassword()));
    }
}