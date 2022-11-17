package com.mustache.bbs.domain.dto.userAdd;

import com.mustache.bbs.domain.dto.userSelectDto.UserSelectRequest;
import com.mustache.bbs.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddRequest {


    private String username;
    private String password;

    public User toEntity(UserAddRequest userAddRequest) {
        return new User(userAddRequest.getUsername(),userAddRequest.getPassword());
    }
}
