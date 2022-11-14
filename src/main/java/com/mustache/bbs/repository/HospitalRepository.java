package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    //*특정 구의 - 보건진료소, 보건지소, 보건소 모두 찾기*//*
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);

    List<Hospital> findByRoadNameAddressContaining(String keyword); // 포함

    List<Hospital> findByHospitalNameContaining(String keyword); // 시작값이 keyword

    List<Hospital> findByHospitalNameStartsWith(String keyword); // 시작값이 keyword

    List<Hospital> findByHospitalNameEndsWith(String keyword); // 끝남값이 keyword

    // 병상 수가 10개 이상 20개 미만인 병원을 모두 찾기
    List<Hospital> findByTotalNumberOfBedsBetween(Integer start, Integer end);
}

