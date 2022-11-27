package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.BookCompany.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

    @EntityGraph(attributePaths ={"publisher","author"})
    Optional<Book> findById(Long id);
}
