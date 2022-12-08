package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.visit.VisitCreateRequest;
import com.mustache.bbs.domain.dto.visit.VisitCreateResponse;
import com.mustache.bbs.domain.dto.visit.VisitSelectResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.domain.entity.Visit;
import com.mustache.bbs.exceptionManager.ErrorCode;
import com.mustache.bbs.exceptionManager.HospitalReviewException;
import com.mustache.bbs.repository.HospitalRepository;
import com.mustache.bbs.repository.UserRepository;
import com.mustache.bbs.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service @Slf4j
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    //create
    public VisitCreateResponse create(VisitCreateRequest visitCreateRequest,String userName) {
        //hospitalid disease amount

        //병원 id로 병원 엔티티 영속성컨텍스트에서 꺼내옴
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(visitCreateRequest.getHospitalId());
        Hospital hospital = hospitalOptional
                .orElseThrow(() -> new HospitalReviewException(ErrorCode.NOT_FOUNDED, "해당 병원은 존재하지 않습니다"));
        //회원 이름으로 회원 엔티티 영속성컨텍스트에서 꺼내옴
        Optional<User> userOptional = userRepository.findUserByUsername(userName);
        User user = userOptional.orElseThrow(() -> new RuntimeException("해당 회원은 존재하지 않습니다"));

        //RequestDto - > Entity
        Visit visit = Visit.builder()
                .user(user)
                .hospital(hospital)
                .disease(visitCreateRequest.getDisease())
                .count(visitCreateRequest.getCount())
                .build();

        Visit savedVisit = visitRepository.save(visit);

        //Entity -> ResponseDto
        VisitCreateResponse visitCreateResponse = VisitCreateResponse.builder()
                .hospitalId(savedVisit.getHospital().getId())
                .disease(savedVisit.getDisease())
                .count(savedVisit.getCount()).build();

        return visitCreateResponse;
    }
    //방문 ID로 조회
    public VisitSelectResponse getOne(Long id) {
        Optional<Visit> visitOptional = visitRepository.findById(id);
        Visit visit = visitOptional.orElseThrow(() ->new RuntimeException("해당 병원방문에 대한 정보는 존재하지 않습니다"));

        //VisitSelectResponse 클래스안에 생성자에서 ResponseDto로 바꿔줌 -> id,username,disease,count
        VisitSelectResponse visitSelectResponse = new VisitSelectResponse(visit);
        return visitSelectResponse;
    }

    //방문 LIST 조회
    public List<VisitSelectResponse> getList() {
        List<Visit> visitList = visitRepository.findAll();
        List<VisitSelectResponse> visitSelectResponseList = visitList.stream().map(visit -> new VisitSelectResponse(visit))
                .collect(Collectors.toList());
        return visitSelectResponseList;
    }

    //GET /api/v1/visits/users/{id} → 특정 user의 기록 조회
    public List<VisitSelectResponse> getByUserInfoToVisit(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseThrow(() -> new RuntimeException("해당 회원은 존재하지 않습니다."));
        //방문리스트
        List<VisitSelectResponse> list = getList();
        //방문리스트의 userId와 현재 userId 비교 filter사용
        List<VisitSelectResponse> findUserVisit
                = list.stream().filter(visit -> user.getId()==visit.getUserId())
                .collect(Collectors.toList());
        return findUserVisit;
    }

    //GET /api/v1/visits/hospitals/{id} → 특정 병원의 방문 기록 조회
    public List<VisitSelectResponse> getByHospitalToVisit(Integer id) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);
        //병원 엔티티 찾음
        Hospital hospital
                = hospitalOptional.orElseThrow(() ->
                new HospitalReviewException(ErrorCode.NOT_FOUNDED, "해당 병원의 리뷰는 존재하지 않습니다"));
        //목록 조회
        List<Visit> visitList = visitRepository.findAll();
        //filter를 사용해서 병원 id비교
        List<VisitSelectResponse> findHospitalVisit =
                visitList.stream().filter(visit -> hospital.getId() == visit.getHospital().getId())
                        .map(visit -> new VisitSelectResponse(visit))
                        .collect(Collectors.toList());

        return findHospitalVisit;
    }

}
