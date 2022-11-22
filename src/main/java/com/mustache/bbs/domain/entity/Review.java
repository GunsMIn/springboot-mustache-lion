package com.mustache.bbs.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@AllArgsConstructor
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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hospital_id") // 연관관계의 주인
    private Hospital hospital;

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
        hospital.getReviews().add(this);
    }

}