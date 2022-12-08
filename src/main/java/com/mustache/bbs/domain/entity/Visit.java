package com.mustache.bbs.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity @Builder @AllArgsConstructor @NoArgsConstructor @Getter
@ToString(callSuper = true) @EqualsAndHashCode(callSuper = true)
public class Visit extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospital_id") // 연관관계의 주인
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Disease disease;

    private Integer count;
}
