package com.mustache.bbs.domain.user;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @ToString
public class UserDto {
    private Long id;
    private String userName;
    private String password;
    private String emailAddress;
}
