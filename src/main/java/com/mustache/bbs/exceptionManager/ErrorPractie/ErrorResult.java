package com.mustache.bbs.exceptionManager.ErrorPractie;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorCode {

    private String code;
    private String message;
}
