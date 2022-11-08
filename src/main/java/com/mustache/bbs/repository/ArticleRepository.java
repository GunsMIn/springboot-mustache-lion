package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface ArticleRepository extends JpaRepository<Article,Long> {

}
