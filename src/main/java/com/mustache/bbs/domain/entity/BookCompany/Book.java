package com.mustache.bbs.domain.entity.BookCompany;

import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String name; // 책이름

    @OneToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne // 근본적으로 생각하면 Book이 Author의 fk를 갖고있다.
    @JoinColumn(name = "author_id")
    private Author author;

}
