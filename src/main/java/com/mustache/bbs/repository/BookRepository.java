package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.BookCompany.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
