package com.mustache.bbs.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.mustache.bbs.domain.entity.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@WebAppConfiguration
@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    @DisplayName("Hospital 정보를 잘 가져오는지 테스트")
    void getHospital() {
        hospitalRepository.findById(1);
        Optional<Hospital> hospital = hospitalRepository.findById(1);
        Hospital hp = hospital.get();
        System.out.println(hp.getId());
        assertEquals(1, hp.getId());
    }

    void printHospitalNameAndAddress(List<Hospital> hospitals) {
        System.out.println(hospitals.size()+"개");
        for (var hospital : hospitals) {
            System.out.printf("%s | %s %f\n", hospital.getHospitalName(), hospital.getRoadNameAddress(), hospital.getTotalAreaSize());
        }


    }


    @Test
    @DisplayName("BusinessTypeName이 보건소 보건지소 보건진료소인 데이터가 잘 나오는지")
    void findByBusinessTypeNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(inClues);
        for (var hospital : hospitals) {
            System.out.println(hospital.getHospitalName());
        }
    }

    @Test
    void containing() {
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining("송파구");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void containing2() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameContaining("닥터스마일");
        for (Hospital hospital : hospitals) {
            if (hospital.getRoadNameAddress().equals("서울특별시 광진구 능동로51길 44 (중곡동)")) {
                System.out.println("hospital = " + hospital.getRoadNameAddress() + "" +
                        "에 있는 "+ hospital.getHospitalName());
            }
        }

    }


    @Test
    void startsWith() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameStartsWith("연세");// 가톨릭 서울 연세 경희
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void endsWith() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameEndsWith("병원");// 의원, 병원, 이비인후과, 치과
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    @DisplayName("병상 수 10개 이상 20개 미만 병원 찾기")
    void findByTotalNumberOfBedsBetween(){
        List<Hospital> hospitalList = hospitalRepository.findByTotalNumberOfBedsBetween(10, 19);
        for (Hospital hospital : hospitalList) {
            System.out.println(hospital.getHospitalName() + " | " + hospital.getTotalNumberOfBeds());
        }
    }

    @Test
    @DisplayName("병상 수 10개 이상 20개 미만 병원 찾기")
    void findByTotalNumberOfBedsBetweenOrderBy(){
        List<Hospital> hospitalList = hospitalRepository.findByTotalNumberOfBedsBetweenOrderByTotalNumberOfBeds(10, 19);
        for (Hospital hospital : hospitalList) {
            System.out.println(hospital.getHospitalName() + " | " + hospital.getTotalNumberOfBeds());

        }
    }
}