package com.mustache.bbs.domain.entity;

import com.mustache.bbs.domain.dto.articleAdd.ArticleAddResponseDto;
import com.mustache.bbs.domain.dto.articleAdd.ArticleAddRequestDto;
import com.mustache.bbs.domain.dto.articleUpdate.ArticleUpdateResponseDto;
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

    //ArticleAddResponseDto
    public static ArticleAddResponseDto transDto(Article article) {
        return new ArticleAddResponseDto(article.getId(), article.getTitle(), article.getContent());
    }

    //Article
    public static ArticleUpdateResponseDto transUpdateDto(Article article) {
        return new ArticleUpdateResponseDto(article.getId(), article.getTitle(), article.getContent());
    }
}
