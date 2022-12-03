package com.mustache.bbs.exceptionManager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor @Getter
public enum ErrorCode {

    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated errorCode"),
    INTERNAR_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error errorCode"),
    NOT_FOUNDED(HttpStatus.NOT_FOUND, "Not Found errorCode"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "Not correct password errorCode");

    private HttpStatus status;
    private String message;

}
