package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping(value = "/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping(value="/posts")
    public String createArticle(ArticleDto form) {
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        // 2. Repository에게 Entity를 DB에 저장하게 한다.
        Article saved = articleRepository.save(articleEntity);
        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/{id}")
    public String read(@PathVariable Long id, Model model) {
        log.info("id:{]",id);

        Optional<Article> opArticle = articleRepository.findById(id);
        Article articleEntity = opArticle.orElseGet(() -> {
            return new Article("찾을 수 없는글의 제목","찾을 수 없는 글의 내용");
        });
        model.addAttribute("article", articleEntity);
        return "articles/show";
    }

    @GetMapping
    public  String getAll(Model model) {
        List<Article> articleEntityList = articleRepository.findAll();
        model.addAttribute("articleList", articleEntityList);
        return "articles/index";
    }





}
