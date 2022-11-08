package com.mustache.bbs.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString
public class Article{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db에 id 생성을 맡긴다
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }



}
