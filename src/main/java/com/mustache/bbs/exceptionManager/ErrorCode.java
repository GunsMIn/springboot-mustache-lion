package com.mustache.bbs.exceptionManager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor @Getter
public enum ErrorCode {

    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated"),
    INTERNAR_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    USER_NOT_FOUNDED(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 맞지 않습니다");

    private HttpStatus status;
    private String message;

}
