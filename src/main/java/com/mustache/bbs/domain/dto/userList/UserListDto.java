package com.mustache.bbs.domain.dto.userList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListDto {
    private Long id;
    private String username;
    private String password;
}
