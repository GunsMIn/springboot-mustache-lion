package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article,Long> {
}
