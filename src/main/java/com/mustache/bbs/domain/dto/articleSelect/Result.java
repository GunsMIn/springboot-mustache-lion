package com.mustache.bbs.domain.dto.articleSelect;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {
    private T data;
    private int count;
}
