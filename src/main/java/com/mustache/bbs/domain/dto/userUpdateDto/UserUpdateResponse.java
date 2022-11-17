package com.mustache.bbs.domain.dto.userUpdateDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateResponse {

    private Long id;
    private String username;
    private String password;

}
