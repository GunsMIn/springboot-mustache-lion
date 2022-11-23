package com.mustache.bbs.domain.entity.BookCompany;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "author_id")
    private Long id;

    private String author; // 작가 이름

    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();

}
