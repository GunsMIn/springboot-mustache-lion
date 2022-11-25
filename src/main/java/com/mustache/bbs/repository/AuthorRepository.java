package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.BookCompany.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
