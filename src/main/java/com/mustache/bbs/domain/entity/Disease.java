package com.mustache.bbs.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum Disease {
    A(1, "감기"),
    B(2, "위염"),
    C(3, "장염"),
    D(4, "코로나"),
    E(5, "근육통"),
    F(6, "코막힘"),
    G(7, "치통"),
    H(8, "원인불명");

    private Integer code;
    private String name;
}
