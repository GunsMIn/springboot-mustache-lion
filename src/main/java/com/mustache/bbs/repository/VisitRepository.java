package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {

}
