package com.mustache.bbs.domain.dto.bookDto;

import com.mustache.bbs.domain.entity.BookCompany.Book;
import lombok.Data;

@Data
public class BookListDto {

    private Long id;
    private String bookName;
    private String authorName;

    public BookListDto(Book book) {
        this.id = book.getId();
        this.bookName = book.getName();
        this.authorName = book.getAuthor().getAuthor();
    }
}
