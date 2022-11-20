package com.mustache.bbs.domain.dto.articleAdd;

import com.mustache.bbs.domain.entity.Article;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleAddRequestDto {
    private String title;
    private String content;

    //requesrDto를 엔티티로!
    //여기가 핵심!

    @Builder
    public ArticleAddRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        Article article = Article.builder()
                .title(title)
                .content(content)
                .build();
        return article;
    }

    public Article toEntity(ArticleAddRequestDto dto) {
        return new Article(dto.title,dto.content);
    }
}
