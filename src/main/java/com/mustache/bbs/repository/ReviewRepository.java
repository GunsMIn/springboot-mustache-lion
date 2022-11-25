package com.mustache.bbs.repository;


import com.mustache.bbs.domain.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByHospitalId(Integer hospitalId);

    @EntityGraph(attributePaths = {"hospital"})
    Optional<Review> findById(Long id);

    @EntityGraph(attributePaths = {"hospital"})
    List<Review> findAll();
}
