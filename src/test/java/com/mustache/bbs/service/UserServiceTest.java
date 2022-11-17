package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.userAdd.UserAddRequest;
import com.mustache.bbs.domain.dto.userAdd.UserAddResponse;
import com.mustache.bbs.domain.dto.userSelectDto.UserSelectResponse;
import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository); // 수동 DI
    }

    @Test
    @DisplayName("회원 등록시 성공 메세지 테스트")
    void add() {
        Mockito.when(userRepository.save(any()))
                .thenReturn(new User(1L, "gunwoo", "kk1234"));

        UserAddResponse userAddResponse
                = userService.addUser(new UserAddRequest("gunwoo", "kk1234"));

        assertEquals("gunwoo",userAddResponse.getUsername());
        assertEquals("회원 등록 성공",userAddResponse.getPassword());
    }

    @Test
    @DisplayName("회원 조회 서비스 테스트")
    void getUser() {
        User user = new User();
        user.setId(123L);
        user.setUsername("leadNumber");
        user.setPassword("kk8888");

        Mockito.when(userRepository.findById(123L))
                .thenReturn(Optional.of(user));

        UserSelectResponse userSelectResponse = userService.findUser(123L);

        assertEquals(userSelectResponse.getId(),user.getId());
        assertEquals(userSelectResponse.getUsername(),user.getUsername());
        assertEquals(userSelectResponse.getPassword(),"회원 등록 성공");

        verify(userRepository).findById(123L);
    }

}