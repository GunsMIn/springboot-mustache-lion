package com.mustache.bbs.domain.dto.articleUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateResponseDto {
    private Long id;
    private String title;
    private String content;
}
