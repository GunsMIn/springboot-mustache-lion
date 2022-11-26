package com.mustache.bbs.domain.dto.hospitalDto;

import com.mustache.bbs.domain.entity.Hospital;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class HospitalWithReview {

    private Integer id;
    private String hospitalName;
    private String roadNameAddress;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;
    private List<ReviewDto> reviews;
    private String count;

    //영업중,폐업중 -> hospital엔티티의 businessStatusCode 에 따라서 영업중,폐업중
    private String businessStatusName;


    public void setBusinessStatusName(String businessStatusName) {
        this.businessStatusName = businessStatusName;
    }


    public HospitalWithReview(Hospital hospital) {
        this.id = hospital.getId();
        this.hospitalName = hospital.getHospitalName();
        this.roadNameAddress = hospital.getRoadNameAddress();
        this.patientRoomCount = hospital.getPatientRoomCount();
        this.totalNumberOfBeds = hospital.getTotalNumberOfBeds();
        this.businessTypeName = hospital.getBusinessTypeName();
        this.totalAreaSize = hospital.getTotalAreaSize();
        this.reviews = hospital.getReviews().stream()
                .map(r->new ReviewDto(r)).collect(Collectors.toList());
        this.count = "해당 병원의 리뷰 개수 :"+ hospital.getReviews().size()+ "개";

    }
}
