package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.visit.VisitCreateRequest;
import com.mustache.bbs.domain.dto.visit.VisitCreateResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.Visit;
import com.mustache.bbs.exceptionManager.ErrorCode;
import com.mustache.bbs.exceptionManager.HospitalReviewException;
import com.mustache.bbs.repository.HospitalRepository;
import com.mustache.bbs.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;

    //create
    public VisitCreateResponse create(VisitCreateRequest visitCreateRequest) {
        //hospitalid disease amount
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(visitCreateRequest.getHospitalId());
        Hospital hospital = hospitalOptional
                .orElseThrow(() -> new HospitalReviewException(ErrorCode.NOT_FOUNDED, "해당 병원은 존재하지 않습니다"));

        Visit visit = Visit.builder()
                .hospital(hospital)
                .disease(visitCreateRequest.getDisease())
                .count(visitCreateRequest.getCount())
                .build();

        Visit savedVisit = visitRepository.save(visit);

        VisitCreateResponse visitCreateResponse = VisitCreateResponse.builder()
                .hospitalId(savedVisit.getHospital().getId())
                .disease(savedVisit.getDisease())
                .count(savedVisit.getCount()).build();

        return visitCreateResponse;
    }
}
