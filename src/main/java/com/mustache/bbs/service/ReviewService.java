package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.reviewDto.ReviewCreateRequest;
import com.mustache.bbs.domain.dto.reviewDto.ReviewCreateResponse;
import com.mustache.bbs.domain.dto.reviewDto.ReviewSelectResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.Review;
import com.mustache.bbs.repository.HospitalRepository;
import com.mustache.bbs.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;


    public ReviewCreateResponse add(ReviewCreateRequest reviewCreateRequest) {
        //hospital 먼저 조회해주자
        int hospitalId = reviewCreateRequest.getHospitalId();
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(hospitalId);
        Hospital hospital = hospitalOptional.get(); // 해당 병원 객체

        //dto -> entity
        Review review = Review.builder()
                .title(reviewCreateRequest.title)
                .content(reviewCreateRequest.content)
                .userName(reviewCreateRequest.userName)
                .hospital(hospital).build();
        //save(Review)
        Review savedReview = reviewRepository.save(review);

        ReviewCreateResponse reviewCreateResponse = new ReviewCreateResponse(savedReview);
        return reviewCreateResponse;
    }

    public Review findById(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        Review review = reviewOptional.get();
        return review;
    }

    public List<ReviewSelectResponse> findAll() {
        List<Review> reviews = reviewRepository.findAll();

        List<ReviewSelectResponse> reviewSelectResponses =
                reviews.stream().map(r -> new ReviewSelectResponse(r)).collect(Collectors.toList());

        return reviewSelectResponses;
    }

}
