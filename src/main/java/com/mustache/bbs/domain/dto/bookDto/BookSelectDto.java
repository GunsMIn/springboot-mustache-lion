package com.mustache.bbs.domain.dto.bookDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSelectDto {

    private Long id;
    private String bookName;
    private String publisherName;
    private String publisherAddress;
    private String authorName;
}
