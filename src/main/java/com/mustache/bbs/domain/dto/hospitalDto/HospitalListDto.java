package com.mustache.bbs.domain.dto.hospitalDto;

import com.mustache.bbs.domain.entity.Hospital;
import lombok.*;

import javax.persistence.Column;

import static lombok.AccessLevel.*;

@Getter @NoArgsConstructor(access = PROTECTED)
public class HospitalListDto { // response Dto

    private Integer id;
    private String roadNameAddress;
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Integer businessStatusCode; // 비지니스 로직에서 businessStatusCode로 영업,폐업 결정
    private Float totalAreaSize;


    @Builder
    public HospitalListDto(Integer id, String roadNameAddress, String hospitalName, Integer patientRoomCount, Integer totalNumberOfBeds, String businessTypeName, Integer businessStatusCode, Float totalAreaSize) {
        this.id = id;
        this.roadNameAddress = roadNameAddress;
        this.hospitalName = hospitalName;
        this.patientRoomCount = patientRoomCount;
        this.totalNumberOfBeds = totalNumberOfBeds;
        this.businessTypeName = businessTypeName;
        this.businessStatusCode = businessStatusCode;
        this.totalAreaSize = totalAreaSize;
    }

    public Hospital toEntity() {
        return Hospital.builder()
                .id(id)
                .roadNameAddress(roadNameAddress)
                .hospitalName(hospitalName)
                .patientRoomCount(patientRoomCount)
                .totalNumberOfBeds(totalNumberOfBeds)
                .businessTypeName(businessTypeName)
                .businessStatusCode(businessStatusCode)
                .totalAreaSize(totalAreaSize)
                .build();
    }
}
