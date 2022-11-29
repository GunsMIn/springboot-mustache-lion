package com.mustache.bbs.domain.user;
import com.mustache.bbs.domain.entity.User;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @ToString
public class UserJoinRequest {
    private String userName;
    private String password;
    private String emailAddress;


    //dto -> entity
    public User toEntity(String password) {
        return User.builder()
                .username(this.userName)
                .password(password)
                .emailAddress(this.emailAddress)
                .build();
    }
}