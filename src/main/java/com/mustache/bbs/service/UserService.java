package com.mustache.bbs.service;

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

    public UserSelectResponse findUser(UserSelectRequest userSelectRequest) {
        User user = userSelectRequest.toEntity(userSelectRequest);
        Optional<User> userOptional = userRepository.findById(user.getId());
        User findUser = userOptional.orElse(new User());
        UserSelectResponse userSelectResponse = User.transSelectDto(findUser);
        return userSelectResponse;
    }

}
