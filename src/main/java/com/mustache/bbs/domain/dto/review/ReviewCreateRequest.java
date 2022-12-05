package com.mustache.bbs.domain.dto.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @Getter @NoArgsConstructor
public class ReviewCreateRequest {
    private Long hospitalId;
    private String title;
    private String content;
    private String userName;
}
