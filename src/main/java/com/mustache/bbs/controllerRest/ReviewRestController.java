package com.mustache.bbs.controllerRest;

import com.mustache.bbs.domain.dto.reviewDto.ReviewCreateRequest;
import com.mustache.bbs.domain.dto.reviewDto.ReviewDto;
import com.mustache.bbs.domain.entity.Review;
import com.mustache.bbs.repository.ReviewRepository;
import com.mustache.bbs.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/reviews")
public class ReviewRestController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    @GetMapping("/{id}")
    public ReviewDto getReview(@PathVariable Long id) {
        Review review = reviewService.findById(id);
        ReviewDto reviewDto = new ReviewDto(review);
        return reviewDto;
    }


    @PostMapping
    public Review addReview(@RequestBody Review review) {
        log.info("review :{}",review);
        Long reviewId = reviewService.save(review);
        Review savedReview = reviewService.findById(reviewId);
        return savedReview;
    }
}

