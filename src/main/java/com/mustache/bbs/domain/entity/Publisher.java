package com.mustache.bbs.domain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Publisher {

    @Id
    @GeneratedValue
    @Column(name = "publisher_id")
    private Long id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();
}
