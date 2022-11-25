package com.mustache.bbs.domain.dto.reviewDto;

import com.mustache.bbs.domain.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReviewSelectResponse {

    private Long id;

    private String title;

    private String content;

    private String userName;

    private String hospitalName;

    private String roadNameAddress;

    private String message;


    public ReviewSelectResponse(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.userName = review.getUserName();
        this.hospitalName = review.getHospital().getHospitalName();
        this.roadNameAddress = review.getHospital().getRoadNameAddress();
        this.message = "리뷰 조회가 성공 했습니다.";
    }
}
