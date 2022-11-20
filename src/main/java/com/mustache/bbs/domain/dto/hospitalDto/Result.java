package com.mustache.bbs.domain.dto.hospitalDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Result<T> {

    private Integer count; // 병원 개수 count
    private T data; // <= 제네릭 타입 HospitalListDto 타입

}
