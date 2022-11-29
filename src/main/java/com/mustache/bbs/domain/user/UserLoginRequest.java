package com.mustache.bbs.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @Getter @NoArgsConstructor
public class UserLoginRequest {

    private String userName;
    private String password;
}
