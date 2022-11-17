package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.userAdd.UserAddRequest;
import com.mustache.bbs.domain.dto.userAdd.UserAddResponse;
import com.mustache.bbs.domain.dto.userSelectDto.UserSelectRequest;
import com.mustache.bbs.domain.dto.userSelectDto.UserSelectResponse;
import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserSelectResponse findUser(Long id) {

        Optional<User> userOptional = userRepository.findById(id);
        User findUser = userOptional.orElse(new User());
        UserSelectResponse userSelectResponse = User.transSelectDto(findUser);
        return userSelectResponse;
    }

    public UserAddResponse addUser(UserAddRequest userAddRequest) {
        User user = userAddRequest.toEntity(userAddRequest);
        User savedUser = userRepository.save(user);
        UserAddResponse userAddResponse = User.transAddDto(savedUser);
        return userAddResponse;
    }

}
