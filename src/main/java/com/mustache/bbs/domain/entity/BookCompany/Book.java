package com.mustache.bbs.domain.entity.BookCompany;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity @Getter
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



}
