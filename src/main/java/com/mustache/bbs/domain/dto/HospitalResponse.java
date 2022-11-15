package com.mustache.bbs.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class HospitalResponse {
    private Integer id;
    private String hospitalName;
    private String roadNameAddress;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;



    public HospitalResponse(Integer id, String hospitalName, String roadNameAddress, Integer patientRoomCount, Integer totalNumberOfBeds, String businessTypeName, Float totalAreaSize) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.roadNameAddress = roadNameAddress;
        this.patientRoomCount = patientRoomCount;
        this.totalNumberOfBeds = totalNumberOfBeds;
        this.businessTypeName = businessTypeName;
        this.totalAreaSize = totalAreaSize;
    }
}
