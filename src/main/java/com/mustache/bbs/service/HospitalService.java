package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    //business_status_code
    //13 - 진료중
    //3 - 폐업

    public HospitalResponse getHospital(Integer id) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);

        Hospital hospital = hospitalOptional.get();//엔티티

        HospitalResponse hospitalResponse = Hospital.transDto(hospital); // dto

        //밑에가 진짜 비지니스 로직 BusinessStatusCode가 13이면 BusinessStatusCode가 3이면 폐업
        if (hospital.getBusinessStatusCode() == 13) {
            hospitalResponse.setBusinessStatusName("영업중");
        } else if (hospital.getBusinessStatusCode() == 3) {
            hospitalResponse.setBusinessStatusName("폐업");
        }else{
            hospitalResponse.setBusinessStatusName(String.valueOf(hospital.getBusinessStatusCode()));
        }
        return hospitalResponse;
    }
}
