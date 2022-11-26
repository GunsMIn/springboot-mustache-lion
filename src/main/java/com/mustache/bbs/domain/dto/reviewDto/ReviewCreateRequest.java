package com.mustache.bbs.domain.dto.reviewDto;

import com.mustache.bbs.domain.entity.Review;
import lombok.*;


@NoArgsConstructor
@Getter
public class ReviewCreateRequest {

    public int hospitalId;
    public String title;
    public String content;
    public String userName;

    //엔티티로 바꾸기
    public Review toEntity() {
        return Review.builder()
                .title(this.title)
                .content(this.content)
                .userName(this.userName)
                .build();
    }

    @Builder
    public ReviewCreateRequest(int hospitalId, String title, String content, String userName) {
        this.hospitalId = hospitalId;
        this.title = title;
        this.content = content;
        this.userName = userName;
    }
}
