package com.mustache.bbs.domain.dto.userAdd;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAddResponse {

    private Long id;
    private String username;
    private String password;

}
