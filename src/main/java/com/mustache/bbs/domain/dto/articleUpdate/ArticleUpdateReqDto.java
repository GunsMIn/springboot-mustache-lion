package com.mustache.bbs.domain.dto.articleUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateReqDto {
    private Long id;
    private String title;
    private String content;


}
