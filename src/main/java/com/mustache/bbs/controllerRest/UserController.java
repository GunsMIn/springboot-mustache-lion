package com.mustache.bbs.controllerRest;

import com.mustache.bbs.domain.Response;
import com.mustache.bbs.domain.user.*;
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
        UserJoinResponse userJoinResponse = new UserJoinResponse(userDto.getUserName(), userDto.getEmailAddress());
        return Response.success(userJoinResponse);
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        log.info("userLoginRequest : {} ",userLoginRequest);
       String token = userService.login(userLoginRequest.getUserName(), userLoginRequest.getPassword());
        return Response.success(new UserLoginResponse(token));
    }
}