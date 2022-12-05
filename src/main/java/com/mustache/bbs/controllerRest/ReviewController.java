package com.mustache.bbs.controllerRest;

import com.mustache.bbs.domain.dto.review.ReviewCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @PostMapping
    public String write(@RequestBody ReviewCreateRequest reviewCreateRequest) {

        return "리뷰 등록에 성공 했습니다";
    }
}
