package com.mustache.bbs.domain.entity.BookCompany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity @Getter @NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String name; // 책이름

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne(fetch = LAZY) // 근본적으로 생각하면 Book이 Author의 fk를 갖고있다.
    @JoinColumn(name = "author_id")
    private Author author;

    @Builder
    public Book(Long id, String name, Publisher publisher, Author author) {
        this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.author = author;
    }
}
