package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import com.mustache.bbs.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.expression.spel.ast.OpOr;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class HospitalServiceTest {

    private HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);

    private HospitalService hospitalService;

    @BeforeEach
    void setUp() {
        hospitalService = new HospitalService(hospitalRepository); // 수동 DI
    }


    @Test
    @DisplayName("영업중, 폐업중 test!")
    void check() {
        //{
        //    "id": 71911,
        //    "hospitalName": "연세우리치과의원",
        //    "roadNameAddress": "광주광역시 북구 서하로 104, 3층 (매곡동)",
        //    "patientRoomCount": 0,
        //    "totalNumberOfBeds": 0,
        //    "businessTypeName": "치과의원",
        //    "totalAreaSize": 221.48,
        //    "businessStatusName": "폐업"
        //}
        Hospital hospital = new Hospital(71911,
                "광주광역시 북구 서하로 104, 3층 (매곡동)",
                "연세우리치과의원",0,
                0,"치과의원",
                3,221.48F);

        Hospital hospital2 = new Hospital(49681,
                "서울특별시 광진구 능동로51길 44 (중곡동)",
                "닥터스마일의원",0,
                0,"의원",
                13,72.87f);


        Mockito.when(hospitalRepository.findById(71911))
                                    .thenReturn(Optional.of(hospital));
        Mockito.when(hospitalRepository.findById(49681))
                .thenReturn(Optional.of(hospital2));


        HospitalResponse hospitalResponse = hospitalService.getHospital(71911);
        HospitalResponse hospitalResponse2 = hospitalService.getHospital(49681);

        assertEquals(hospitalResponse.getHospitalName(),hospital.getHospitalName());
        assertEquals(hospitalResponse.getRoadNameAddress(),hospital.getRoadNameAddress());
        assertEquals(hospitalResponse.getBusinessStatusName(),"폐업");

        assertEquals(hospitalResponse2.getHospitalName(),hospital2.getHospitalName());
        assertEquals(hospitalResponse2.getRoadNameAddress(),hospital2.getRoadNameAddress());
        assertEquals(hospitalResponse2.getBusinessStatusName(),"영업중");

    }

}