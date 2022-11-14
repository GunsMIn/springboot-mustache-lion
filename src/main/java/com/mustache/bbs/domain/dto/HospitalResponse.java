package com.mustache.bbs.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class HospitalResponse {
    private Integer id;
    private String roadNameAddress;
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;


    public HospitalResponse(Integer id, String roadNameAddress, String hospitalName) {
        this.id = id;
        this.roadNameAddress = roadNameAddress;
        this.hospitalName = hospitalName;
    }

    public HospitalResponse(Integer id, String roadNameAddress, String hospitalName, Integer patientRoomCount, Integer totalNumberOfBeds, String businessTypeName, Float totalAreaSize) {
        this.id = id;
        this.roadNameAddress = roadNameAddress;
        this.hospitalName = hospitalName;
        this.patientRoomCount = patientRoomCount;
        this.totalNumberOfBeds = totalNumberOfBeds;
        this.businessTypeName = businessTypeName;
        this.totalAreaSize = totalAreaSize;
    }
}
