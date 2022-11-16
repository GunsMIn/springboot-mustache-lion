package com.mustache.bbs.domain.entity;

import com.mustache.bbs.domain.dto.ArticleDto;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
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


    public static ArticleDto transDto(Article article) {
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent());
    }

}
