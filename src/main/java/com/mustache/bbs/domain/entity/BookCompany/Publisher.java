package com.mustache.bbs.domain.entity.BookCompany;

import com.mustache.bbs.domain.entity.BookCompany.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Publisher {

    @Id
    @GeneratedValue
    @Column(name = "publisher_id")
    private Long id;

    private String name; // 출판사 이름

    private String address; // 출판사 주소

    @OneToMany(mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();
}
