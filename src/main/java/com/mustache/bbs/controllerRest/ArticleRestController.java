package com.mustache.bbs.controllerRest;

import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleRestController {

    private final ArticleService articleService;

    @GetMapping("/api/article/{id}")
    public ResponseEntity<ArticleDto> get(@PathVariable Long id) {
        ArticleDto articleDto = articleService.getArticle(id);
        return ResponseEntity.status(HttpStatus.OK).body(articleDto);
    }

}
