package com.mustache.bbs.repository;


import com.mustache.bbs.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //hospitalId로 해당 병원의 review들 반환
    List<Review> findByHospitalId(Integer hospitalId);
}
