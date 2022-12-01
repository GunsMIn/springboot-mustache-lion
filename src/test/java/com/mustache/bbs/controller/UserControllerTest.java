package com.mustache.bbs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.bbs.controllerRest.UserRestController;
import com.mustache.bbs.domain.dto.userAdd.UserAddRequest;
import com.mustache.bbs.domain.dto.userAdd.UserAddResponse;
import com.mustache.bbs.domain.dto.userSelectDto.UserSelectRequest;
import com.mustache.bbs.domain.dto.userSelectDto.UserSelectResponse;
import com.mustache.bbs.domain.user.UserJoinRequest;
import com.mustache.bbs.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;


    @Test
    @DisplayName("회원가입 성공")
    void join_success() throws Exception {
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("kimgunwoo")
                .password("kk1234")
                .emailAddress("gunwoo4670@gmail.com")
                .build();


    }









    @Test
    @DisplayName("회원 단건 조회 테스트")
    void getOne() throws Exception {
        //  4 1234 김영기
        UserSelectRequest userSelectRequest = UserSelectRequest.builder()
                .id(9L)
                .name("멋쟁이사자")
                .build();

        given(userService.findUser(9L))
                .willReturn(new UserSelectResponse(9L, "멋쟁이사자", "1234"));

        Long userId = 9L;

        String url = String.format("/api/users/%d", userId);
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.username").value("멋쟁이사자"))
                .andExpect(jsonPath("$.password").exists())
                .andExpect(jsonPath("$.password").value("1234"))
                .andDo(print());

        verify(userService).findUser(userId);
    }

    @Test
    @DisplayName("회원 넣기 컨트롤러 테스트")
    void add() throws Exception {
        UserAddRequest userAddRequest =
                 new UserAddRequest("김건우", "kk6251");
        given(userService.addUser(any(UserAddRequest.class)))
                .willReturn(new UserAddResponse(10L,userAddRequest.getUsername(), userAddRequest.getPassword()));

        mockMvc.perform(post("/api/users")
                .content(objectMapper.writeValueAsBytes(userAddRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.username").value("김건우"))
                .andExpect(jsonPath("$.password").exists())
                .andExpect(jsonPath("$.password").value("kk6251"))
                .andDo(print());

        verify(userService).addUser(ArgumentMatchers.refEq(userAddRequest));
    }


}