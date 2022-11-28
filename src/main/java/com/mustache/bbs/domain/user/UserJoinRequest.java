package com.mustache.bbs.domain.user;
import com.mustache.bbs.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter @ToString
public class UserJoinRequest {
    private String userName;
    private String password;
    private String emailAddress;


    //dto -> entity
    public User toEntity() {
        return User.builder()
                .username(userName)
                .password(password)
                .emailAddress(emailAddress)
                .build();
    }
}