package com.mustache.bbs.domain.entity;

import com.mustache.bbs.domain.dto.userAdd.UserAddResponse;
import com.mustache.bbs.domain.dto.userSelectDto.UserSelectResponse;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;


   /* public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
*/
    @Builder
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
