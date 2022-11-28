package com.mustache.bbs.domain.user;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class UserDto {
    private String userName;
    private String password;
    private String emailAddress;
}
