package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.articleAdd.ArticleDto;
import com.mustache.bbs.domain.dto.articleAdd.ArticleRequestDto;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;

    //한개의 게시글을 조회하는 기능
    public ArticleDto getArticle(Long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        Article article = articleOptional.orElse(new Article());
        ArticleDto articleDto = Article.transDto(article);
        return articleDto;
    }

    //add
    public ArticleDto addArticle(ArticleRequestDto dto) {

        Article article = dto.toEntity(dto);
        Article savedArticle = articleRepository.save(article);
        ArticleDto articleDto = Article.transDto(savedArticle);
        return articleDto;

    }

}
