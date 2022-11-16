package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleDto getArticle(Long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        Article article = articleOptional.orElse(new Article());
        ArticleDto articleDto = Article.transDto(article);
        return articleDto;
    }

}
