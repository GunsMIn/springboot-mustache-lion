package com.mustache.bbs.controllerRest;

import com.mustache.bbs.domain.entity.Review;
import com.mustache.bbs.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/review")
public class ReviewRestController {

    private final ReviewRepository reviewRepository;

    @PostMapping
    public Review addReview(@RequestBody Review review) {
        review.setHospital(review.getHospital());
        Review save = reviewRepository.save(review);
        return save;

    }
}

