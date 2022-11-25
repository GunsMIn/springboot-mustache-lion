package com.mustache.bbs.domain.dto.reviewDto;

import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.Review;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewCreateResponse {
    private Long id;

    private String title;

    private String content;

    private String userName;

    private String hospitalName;

    private String roadNameAddress;

    private String message;


    public ReviewCreateResponse(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.userName = review.getUserName();
        this.hospitalName = review.getHospital().getHospitalName();
        this.roadNameAddress = review.getHospital().getRoadNameAddress();
        this.message = "리뷰 등록이 성공 했습니다.";
    }
}
