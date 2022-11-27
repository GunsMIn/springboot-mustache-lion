package com.mustache.bbs.domain.dto.bookDto;

import com.mustache.bbs.domain.entity.BookCompany.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookCreateResponse {

    private Long id;
    private String name;
    private String publisherName;
    private String authorName;

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    //entity -> responseDto 메소드
    public BookCreateResponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.publisherName = book.getPublisher().getName();
        this.authorName = book.getAuthor().getAuthor();
    }
}
