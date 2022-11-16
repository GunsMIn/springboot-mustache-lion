package com.mustache.bbs.domain.dto.articleDelte;

import com.mustache.bbs.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDeleteReqDto {
    //삭제는 id만 필요
    private Long id;

    public Article toEntity(ArticleDeleteReqDto articleDeleteReqDto) {
        return new Article(articleDeleteReqDto.getId());
    }
}
