package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long> {


}
