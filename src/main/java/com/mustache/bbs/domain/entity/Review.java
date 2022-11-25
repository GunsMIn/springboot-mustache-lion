package com.mustache.bbs.domain.entity;

import com.mustache.bbs.domain.dto.reviewDto.ReviewCreateRequest;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity @ToString
@NoArgsConstructor
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Column(name="user_name")
    private String userName;

    @ManyToOne(fetch = LAZY) // 리뷰가 병원의 fk를 갖고있다.
    @JoinColumn(name = "hospital_id") // 연관관계의 주인
    private Hospital hospital;

    public void addReview(Hospital hospital) {
        this.hospital = hospital;
        hospital.getReviews().add(this);
    }
    @Builder
    public Review(Long id, String title, String content, String userName, Hospital hospital) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.hospital = hospital;
    }
}