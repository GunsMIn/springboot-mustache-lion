package com.mustache.bbs.domain.entity;

import javax.persistence.*;

@Entity
public class Visit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospital_id") // 연관관계의 주인
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated
    private Disease disease;

    private Integer count;
}
