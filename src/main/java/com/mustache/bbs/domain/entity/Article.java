package com.mustache.bbs.domain.entity;

import com.mustache.bbs.domain.dto.articleAdd.ArticleDto;
import com.mustache.bbs.domain.dto.articleAdd.ArticleRequestDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class Article{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;


    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static ArticleDto transDto(Article article) {
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent());
    }

    public static ArticleDto reqTransDto(ArticleRequestDto articleRequestDto) {
        return new ArticleDto(articleRequestDto.getTitle(), articleRequestDto.getContent());
    }

}
