package com.mustache.bbs.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString
public class Article{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db에 id 생성을 맡긴다
    @Column(name = "article_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @OneToMany(mappedBy = "article",cascade =REMOVE) // 글이 삭제되면 댓글도 삭제되어야하기때문에
    List<Comment> comments = new ArrayList<>();

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }



}
