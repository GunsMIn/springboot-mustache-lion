package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.hospitalDto.HospitalResponse;
import com.mustache.bbs.domain.dto.hospitalDto.HospitalListDto;
import com.mustache.bbs.domain.dto.hospitalDto.HospitalWithReview;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    //병원만 조회
    //business_status_code
    //13 - 진료중
    //3 - 폐업
    public HospitalResponse getHospital(Integer id) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);

        Hospital hospital = hospitalOptional.orElse(new Hospital()); // 엔티티로 꺼내옴

        HospitalResponse hospitalResponse = Hospital.transDto(hospital);

        //밑에가 진짜 비지니스 로직 BusinessStatusCode가 13이면 BusinessStatusCode가 3이면 폐업
        if (hospital.getBusinessStatusCode() == 13) {
            hospitalResponse.setBusinessStatusName("영업중");
        } else if(hospital.getBusinessStatusCode()==3){
            hospitalResponse.setBusinessStatusName("폐업");
        }else{
            hospitalResponse.setBusinessStatusName("진료 시간을 병원에 문의해 보세요");
        }
        return hospitalResponse;
    }

    //병원과 리뷰 조회
    public HospitalWithReview getHospitalWithReview(Integer id) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);
        Hospital hospital = hospitalOptional.orElseThrow(()->
        new IllegalStateException("no id")); // 엔티티로 꺼내옴

        HospitalWithReview hospitalWithReview = new HospitalWithReview(hospital);

        if (hospital.getBusinessStatusCode() == 13) {
            hospitalWithReview.setBusinessStatusName("영업중");
        } else if(hospital.getBusinessStatusCode()==3){
            hospitalWithReview.setBusinessStatusName("폐업");
        }else{
            hospitalWithReview.setBusinessStatusName("진료 시간을 병원에 문의해 보세요");
        }
        return hospitalWithReview;
    }
    
    //리뷰가 존재하는 병원만 조회
    public List<HospitalWithReview> findAllExistReview() {
        List<Hospital> hospitalList = hospitalRepository.findAll();
        List<HospitalWithReview> hospitalWithReviews = hospitalList.stream()
                .filter(h -> h.getReviews().size()>0 )
                .map(h -> new HospitalWithReview(h)).collect(Collectors.toList());
        return hospitalWithReviews;
    }

    //findAll to Json
    public List<HospitalListDto> findAll() {
        List<Hospital> hospitalList = hospitalRepository.findAll();
        List<HospitalListDto> hospitalListDtoList = hospitalList.stream()
                .map(h -> Hospital.of(h)).collect(Collectors.toList());

        return hospitalListDtoList;
    }

}
