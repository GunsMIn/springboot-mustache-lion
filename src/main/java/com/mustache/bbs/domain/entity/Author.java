package com.mustache.bbs.domain.entity;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "author_id")
    private Long id;

    private String author;

    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();

}
