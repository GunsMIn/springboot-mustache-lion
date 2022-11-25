package com.mustache.bbs.domain.dto.reviewDto;

import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.Review;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Data
public class ReviewDto {
    private Long id;

    private String title;

    private String content;

    private String userName;

    private String roadNameAddress;

    private String hospitalName;


    public ReviewDto(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.userName = review.getUserName();
        this.roadNameAddress = review.getHospital().getRoadNameAddress();
        this.hospitalName = review.getHospital().getHospitalName();
    }
}
