package com.mustache.bbs.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum Disease {

    A(1, "감기"),
    B(2, "위염"),
    C(3, "장염"),
    D(4, "코로나");

    private Integer code;
    private String name;
}
