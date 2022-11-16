package com.mustache.bbs.domain.dto.articleAdd;

import com.mustache.bbs.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleAddRequestDto {
    private String title;
    private String content;

    //requesrDto를 엔티티로!
    public Article toEntity(ArticleAddRequestDto dto) {
        return new Article(dto.title,dto.content);
    }
}
