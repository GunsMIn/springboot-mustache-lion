package com.mustache.bbs.controller;

import com.mustache.bbs.domain.Response;
import com.mustache.bbs.domain.user.UserDto;
import com.mustache.bbs.domain.user.UserJoinRequest;
import com.mustache.bbs.domain.user.UserJoinResponse;
import com.mustache.bbs.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController @Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest) {
        log.info("userJoinRequest :{} ", userJoinRequest);
        UserDto userDto = userService.join(userJoinRequest);
        log.info("userDto :{} ", userDto);
        return Response.success(new UserJoinResponse(userDto.getUserName(), userDto.getEmailAddress()));
    } 
}