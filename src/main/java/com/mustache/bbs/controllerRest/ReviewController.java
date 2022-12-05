package com.mustache.bbs.controllerRest;

import com.mustache.bbs.domain.dto.review.ReviewCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    //SpringSecurity에서 인증된 토큰을 얻은 User만 review를 쓸 수 있는 API
    @PostMapping
    public String write(@RequestBody ReviewCreateRequest dto, Authentication authentication) {
        log.info("Controller user:{}", authentication.getName());
        return "리뷰 등록에 성공 했습니다.";

    }
}
