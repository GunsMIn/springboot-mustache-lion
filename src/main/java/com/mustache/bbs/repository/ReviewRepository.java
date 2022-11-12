package com.mustache.bbs.repository;


import com.mustache.bbs.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //hospitalId�� �ش� ������ review�� ��ȯ
    List<Review> findByHospitalId(Integer hospitalId);
}
