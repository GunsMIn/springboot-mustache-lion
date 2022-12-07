package com.mustache.bbs.exceptionManager;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class HospitalReviewException extends RuntimeException{

    ErrorCode errorCode;
    private String message; //String.format("UserName:%s", userJoinRequest.getUserName())

    @Override
    public String toString() {
        if(message == null) {
            return errorCode.getMessage();
        }
        return String.format("%s. %s", errorCode.getMessage(), message);
    }
}
