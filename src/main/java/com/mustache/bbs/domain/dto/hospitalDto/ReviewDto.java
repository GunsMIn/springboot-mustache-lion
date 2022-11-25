package com.mustache.bbs.domain.dto.hospitalDto;

import com.mustache.bbs.domain.entity.Review;
import lombok.Data;

@Data
public class ReviewDto {

    private String username;
    private String title;
    private String content;

    public ReviewDto(Review review) {
        this.username = review.getUserName();
        this.title = review.getTitle();
        this.content = review.getContent();
    }
}
