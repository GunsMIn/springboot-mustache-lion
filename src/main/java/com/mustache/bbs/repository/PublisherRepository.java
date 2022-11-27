package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.BookCompany.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher,Long> {
}
