package com.mustache.bbs.domain.dto.articleListDto;

import com.mustache.bbs.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListDto {

    private Long id;
    private String title;
    private String content;

    public ArticleListDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return new Article(this.id, this.title, this.content);
    }
}
