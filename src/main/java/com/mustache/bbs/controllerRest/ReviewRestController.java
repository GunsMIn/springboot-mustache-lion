package com.mustache.bbs.controllerRest;

import com.mustache.bbs.domain.dto.reviewDto.ReviewCreateRequest;
import com.mustache.bbs.domain.dto.reviewDto.ReviewCreateResponse;
import com.mustache.bbs.domain.dto.reviewDto.ReviewSelectResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.Review;
import com.mustache.bbs.repository.HospitalRepository;
import com.mustache.bbs.repository.ReviewRepository;
import com.mustache.bbs.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReviewRestController {

    private final ReviewService reviewService;

    //리뷰 id로 리뷰와 병원정보조회
    @GetMapping("/api/{id}/reviews")
    public ResponseEntity<ReviewSelectResponse> getReview(@PathVariable Long id) {
        Review review = reviewService.getReview(id);
        log.info("리뷰: {}",review);
        ReviewSelectResponse reviewDto = new ReviewSelectResponse(review);
        return ResponseEntity.ok().body(reviewDto);
    }

    //리뷰 쓰기
    @PostMapping("/api/{id}/reviews")
    public ResponseEntity<ReviewCreateResponse> writeReview(@PathVariable Integer id, @RequestBody ReviewCreateRequest reviewCreateRequest) {
        ReviewCreateResponse reviewCreateResponse = reviewService.add(reviewCreateRequest);
        return ResponseEntity.ok().body(reviewCreateResponse);
    }

    //리뷰 전체 조회
    @GetMapping("/api/reviews")
    public  ResponseEntity<List<ReviewSelectResponse>> getReviewList() {
        List<ReviewSelectResponse> responseList = reviewService.findAll();
        return ResponseEntity.ok().body(responseList);
    }

}

