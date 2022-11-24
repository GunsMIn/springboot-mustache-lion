package com.mustache.bbs.service;

import com.mustache.bbs.domain.entity.Review;
import com.mustache.bbs.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Long save(Review review) {
        Review savedReview = reviewRepository.save(review);

        return save.getId();
    }

    public Review findById(Long id) {
        Optional<Review> byId = reviewRepository.findById(id);
        Review review = byId.get();
        return review;
    }

}
