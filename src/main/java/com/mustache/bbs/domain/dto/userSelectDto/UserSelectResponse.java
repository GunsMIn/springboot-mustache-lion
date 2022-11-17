package com.mustache.bbs.domain.dto.userSelectDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserSelectResponse {
    private Long id;
    private String username;
    private String password;
}
