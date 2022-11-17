package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.userAdd.UserAddRequest;
import com.mustache.bbs.domain.dto.userAdd.UserAddResponse;
import com.mustache.bbs.domain.dto.userSelectDto.UserSelectRequest;
import com.mustache.bbs.domain.dto.userSelectDto.UserSelectResponse;
import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserSelectResponse findUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User findUser = userOptional.orElse(new User());
        UserSelectResponse userSelectResponse = User.transSelectDto(findUser);
        return userSelectResponse;
    }

    //여기서 비지니스로직으로 이름 중복체크를 진행한다.
    public UserAddResponse addUser(UserAddRequest userAddRequest) {
        //이름으로 list<member>가져옴 -> 하지만 이미 해당이름으로 찾은 list가 비어있지 않으면 이름이 중복됨
        List<User> memberList = userRepository.findByUsername(userAddRequest.getUsername());
        //밑 조건에서 List가 비어있지않으면 -> 이름 중복 오류 던지기
        //비어있으면 이름중복이 안되니까 그대로 회원가입 진행
        if (!memberList.isEmpty()) {
            return new UserAddResponse("이름이 중복되는 회원", "이름이 중복되는 회원");
           // throw new IllegalArgumentException("이름이 이미 존재하는 회원입니다.");
        }

        User user = userAddRequest.toEntity(userAddRequest);
        User savedUser = userRepository.save(user);
        UserAddResponse userAddResponse = User.transAddDto(savedUser);
        return userAddResponse;
    }

}
