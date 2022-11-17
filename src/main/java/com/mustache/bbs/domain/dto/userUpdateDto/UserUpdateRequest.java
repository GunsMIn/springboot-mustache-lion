package com.mustache.bbs.domain.dto.userUpdateDto;

import com.mustache.bbs.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    private Long id;
    private String username;
    private String password;

    //엔티티로 바꾸는 메소드
    User toEntity() {
        return new User(this.id, this.username, this.password);
    }
}
