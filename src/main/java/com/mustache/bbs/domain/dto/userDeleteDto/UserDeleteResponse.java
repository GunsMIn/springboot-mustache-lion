package com.mustache.bbs.domain.dto.userDeleteDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeleteResponse {

    private Long id;
    private String username;
    private String password;
}
