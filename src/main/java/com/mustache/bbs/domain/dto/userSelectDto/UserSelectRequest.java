package com.mustache.bbs.domain.dto.userSelectDto;

import com.mustache.bbs.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSelectRequest {
    private Long id;
    private String name;

    public User toEntity(UserSelectRequest userSelectRequest) {
        return new User(userSelectRequest.getId(),userSelectRequest.getName());
    }
}
