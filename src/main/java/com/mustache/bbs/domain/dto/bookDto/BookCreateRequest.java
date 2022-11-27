package com.mustache.bbs.domain.dto.bookDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor @NoArgsConstructor @ToString
public class BookCreateRequest {

    private Long publisherId;
    private Long authorId;
    private String name;

}
