package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    @Query(value="select h from Hospital h left join h.reviews",
            countQuery ="select count(h) from Hospital h")
    Page<Hospital> findAll(Pageable pageable);

    @EntityGraph(attributePaths={"reviews"})
    Optional<Hospital> findById(int id);

    //*특정 구의 - 보건진료소, 보건지소, 보건소 모두 찾기*//*
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);

    //포함되는 병원명으로 list 병원 찾기(Pageable)
    @Query(value="select h from Hospital h left join h.reviews",
            countQuery ="select count(h) from Hospital h")
    Page<Hospital> findByHospitalNameContaining(String hospitalName,Pageable pageable);

    //포함되는 도로명으로 list 병원 찾기(Pageable) ->
    @Query(value="select h from Hospital h left join h.reviews",
            countQuery ="select count(h) from Hospital h")
    Page<Hospital> findByRoadNameAddressContaining(String keyword,Pageable pageable); // 포함

    //포함되는 병원명으로 병원리스트 찾기
    List<Hospital> findByHospitalNameContaining(String keyword); // 포함값

    //앞글자가 일치하는 병원들 찾기
    List<Hospital> findByHospitalNameStartsWith(String keyword); // 시작값이 keyword 가톨릭% 서울% 연세% 경희%

    //뒤에 글자가 일치하는 병원들 찾기
    List<Hospital> findByHospitalNameEndsWith(String keyword); // 끝남값이 keyword %의원, %병원, %이비인후과, %치과

    //병상 수가 10개 이상 20개 미만인 병원을 모두 찾기
    List<Hospital> findByTotalNumberOfBedsBetween(Integer start, Integer end);

    //병상 수가 10개 이상 20개 미만인 병원을 모두 찾기 오름차순
    List<Hospital> findByTotalNumberOfBedsBetweenOrderByTotalNumberOfBeds(Integer start,Integer end);



}

