package com.mustache.bbs.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

@Data @AllArgsConstructor @Getter @NoArgsConstructor
public class UserLoginResponse {
    private  String token;
}
