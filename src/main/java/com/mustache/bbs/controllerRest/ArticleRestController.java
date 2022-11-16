package com.mustache.bbs.controllerRest;

import com.mustache.bbs.domain.dto.articleAdd.ArticleDto;
import com.mustache.bbs.domain.dto.articleAdd.ArticleRequestDto;
import com.mustache.bbs.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ArticleRestController {

    private final ArticleService articleService;

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleDto> get(@PathVariable Long id) {
        ArticleDto articleDto = articleService.getArticle(id);
        return ResponseEntity.status(HttpStatus.OK).body(articleDto);
    }

    @PostMapping("/api/articles")
    public ResponseEntity<ArticleDto>add(@RequestBody ArticleRequestDto articleRequestDto) {
        log.info("article : {}",articleRequestDto);
        ArticleDto articleDto = articleService.addArticle(articleRequestDto);
        return ResponseEntity.ok().body(articleDto);
    }
}
