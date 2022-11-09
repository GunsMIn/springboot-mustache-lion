package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ArticleController {

    private final ArticleRepository articleRepository;

    //글 작성페이지
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

    //id로 조회 페이지
    @GetMapping("/{id}")
    public String read(@PathVariable Long id, Model model) {
        log.info("id:{]",id);

        Optional<Article> opArticle = articleRepository.findById(id);
        Article articleEntity = opArticle.orElse(null);
        if (articleEntity != null) {
            model.addAttribute("article", articleEntity);
        }else{
            return "error";
        }
        return "articles/show";
    }

    //전체글 리스트 페이지
    @GetMapping
    public  String getAll(Model model) {
        List<Article> articleEntityList = articleRepository.findAll();
        model.addAttribute("articleList", articleEntityList);
        return "articles/index";
    }

    //글 수정 페이지
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Article> opArticle = articleRepository.findById(id);
        Article articlesEntity = opArticle.orElseGet(() -> {
            return new Article("찾을 수 없는글의 제목", "찾을 수 없는 글의 내용");
        });

        model.addAttribute("article", articlesEntity);
        return "articles/edit";
    }

    //글 수정
    @PostMapping("/update")
    public String update(@ModelAttribute ArticleDto dto) {
        log.info(dto.toString());

        //피피티에서는 merge를 사용한거 같지만 변경감지 사용할 것 -> dirty cash
        Long id = dto.getId();
        Article article = articleRepository.findById(id).
                orElseThrow(()->new IllegalArgumentException("해당 글이 업습니다"));
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());

        return "redirect:/articles"+article.getId();
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        Optional<Article> opArticle = articleRepository.findById(id);
        Article article = opArticle.orElse(null);
        articleRepository.delete(article);
        return "redirect:/articles";
    }
}
