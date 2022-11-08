package com.mustache.bbs.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Article{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db에 id 생성을 맡긴다
    private Long id;

    private String title;

    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }



}
