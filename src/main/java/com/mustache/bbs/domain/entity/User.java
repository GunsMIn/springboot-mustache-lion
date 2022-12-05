package com.mustache.bbs.domain.entity;

import com.mustache.bbs.domain.dto.userAdd.UserAddResponse;
import com.mustache.bbs.domain.dto.userSelectDto.UserSelectResponse;
import lombok.*;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String emailAddress;

    @Enumerated(EnumType.STRING)
    private UserRole role;



    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }



    public static UserSelectResponse transSelectDto(User user) {
            return new UserSelectResponse(user.getId(), user.getUsername(), user.getPassword());
    }

    public static UserAddResponse transAddDto(User user) {
        return new UserAddResponse(user.getUsername(), user.getPassword());
    }

}
