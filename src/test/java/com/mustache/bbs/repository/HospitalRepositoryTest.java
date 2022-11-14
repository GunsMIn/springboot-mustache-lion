package com.mustache.bbs.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.mustache.bbs.domain.entity.Hospital;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {
    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    void findBy() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalName("김내과");
        printHospitalNameAndAddress(hospitals);
    }

    void printHospitalNameAndAddress(List<Hospital> hospitals) {
        for (var hospital : hospitals) {
            System.out.printf("%s | %s %f\n", hospital.getHospitalName(), hospital.getRoadNameAddress(), hospital.getTotalAreaSize());
        }

        System.out.println(hospitals.size());
    }

    @Test
    void findByStartingWith() {
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressStartingWith("서울특별시 서초구");
        System.out.println(hospitals.size());
        for (var hospital : hospitals) {
            System.out.printf("%s %s\n", hospital.getHospitalName(), hospital.getRoadNameAddress());
        }
    }

    @Test
    void findByLike() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameLike("%한방%신경%");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void containing() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameContaining("자생");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void between() {
        List<Hospital> hospitals = hospitalRepository.findByTotalAreaSizeBetween(10, 100);
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void orderby() {
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContainingOrderByTotalAreaSize("경기도 수원시");
        printHospitalNameAndAddress(hospitals);
    }
}