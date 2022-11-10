package com.mustache.bbs.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String comment; // 댓글 내용

    @CreatedDate
    private String createdDate;

    @LastModifiedDate
    private String modifiedDate;

    @ManyToOne(fetch = LAZY) // 댓글 입장에서는 다 : 1
    @JoinColumn(name = "article_id")
    private Article article;
}
