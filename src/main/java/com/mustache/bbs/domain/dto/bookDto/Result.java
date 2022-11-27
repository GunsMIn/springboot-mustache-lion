package com.mustache.bbs.domain.dto.bookDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {
    private Integer count;
    private T data;

}
