package com.mustache.bbs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.bbs.controllerRest.UserController;
import com.mustache.bbs.domain.user.UserDto;
import com.mustache.bbs.domain.user.UserJoinRequest;
import com.mustache.bbs.domain.user.UserLoginRequest;
import com.mustache.bbs.exceptionManager.ErrorCode;
import com.mustache.bbs.exceptionManager.HospitalReviewException;
import com.mustache.bbs.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @MockBean
    BCryptPasswordEncoder encoder;

    UserJoinRequest userJoinRequest = UserJoinRequest.builder()
            .userName("kimgunwoo")
            .password("kk4321")
            .emailAddress("gunwoo4670@gmail.com")
            .build();


    @Test
    @DisplayName("회원가입 성공")
    @WithMockUser
    void join_success() throws Exception {
        //join을 하려면 UserJoinRequest(dto가 필요)
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("kimgunwoo")
                .password("kk1234")
                .emailAddress("gunwoo4670@gmail.com")
                .build();
        //when(이러한 메소드를 하면),thenReturn(mock(~) 을 반환한다)
        when(userService.join(any())).thenReturn(mock(UserDto.class));

        mockMvc.perform(post("/api/v1/users/join")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userJoinRequest))) //objectMapper.writeValueAsBytes -> String 타입으로 변환
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패")
    void join_fail() throws Exception {

        when(userService.join(any()))
                .thenThrow(new HospitalReviewException(ErrorCode.DUPLICATED_USER_NAME, ""));

        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("로그인 성공 비지니스로직 2개 통솨")
    @WithMockUser
    void login_success() throws Exception{
        String userName = "kimgunwoo";
        String password = "1234";

        // id, pw를 보내서
        when(userService.login(any(), any()))
                .thenReturn("token");

        // NOT_FOUND를 받으면 잘 만든 것이다
        mockMvc.perform(post("/api/v1/users/login")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 실패 - ID 없을 시")
    @WithMockUser
    void login_fail1() throws Exception{
        String userName = "kimgunwoo";
        String password = "1234";

        // id, pw를 보내서
        when(userService.login(userName, password))
                .thenThrow(new HospitalReviewException(ErrorCode.NOT_FOUNDED, ""));

        // NOT_FOUND를 받으면 잘 만든 것이다
        mockMvc.perform(post("/api/v1/users/login")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password))))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("로그인 실패 - password 오류")
    @WithMockUser
    void login_fail2() throws Exception{
        String userName = "kimgunwoo";
        String password = "1234";
        when(userService.login(any(), any()))
                .thenThrow(new HospitalReviewException(ErrorCode.INVALID_PASSWORD, ""));

        mockMvc.perform(post("/api/v1/users/login").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password)))
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

}