package com.mustache.bbs.domain.entity;

import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.domain.dto.hospitalDto.HospitalListDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
@Table(name = "nation_wide_hospitals")
@NoArgsConstructor
public class Hospital {

    @Id
    private Integer id;
    @Column(name = "road_name_address")
    private String roadNameAddress;
    @Column(name = "hospital_name")
    private String hospitalName;
    @Column(name = "patient_room_count")
    private Integer patientRoomCount;
    @Column(name = "total_number_of_beds")
    private Integer totalNumberOfBeds;
    @Column(name = "business_type_name")
    private String businessTypeName;
    @Column(name = "business_status_code")
    private Integer businessStatusCode; // 비지니스 로직에서 businessStatusCode로 영업,폐업 결정
    @Column(name = "total_area_size")
    private Float totalAreaSize;

    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    /*편의메소드*/
    public void addReview(Review review) {
        reviews.add(review);
        review.setHospital(this);
    }
    
    
    
    // HospitalEntity를 HospitalResponse Dto로 만들어주는 부분
    public static HospitalResponse transDto(Hospital hospital) {
        return new HospitalResponse(hospital.getId(),hospital.getHospitalName(),
                hospital.getRoadNameAddress(),hospital.getPatientRoomCount(),
                hospital.getTotalNumberOfBeds(),hospital.getBusinessTypeName(),
                hospital.getTotalAreaSize());
    }


    // 엔티티 -> HospitalListDto(response)
    public static HospitalListDto of(Hospital hospital) {
        return HospitalListDto.builder()
                .id(hospital.id)
                .roadNameAddress(hospital.roadNameAddress)
                .hospitalName(hospital.hospitalName)
                .patientRoomCount(hospital.patientRoomCount)
                .totalNumberOfBeds(hospital.totalNumberOfBeds)
                .businessTypeName(hospital.businessTypeName)
                .businessStatusCode(hospital.businessStatusCode)
                .totalAreaSize(hospital.totalAreaSize)
                .build();
    }





    @Builder
    public Hospital(Integer id, String roadNameAddress, String hospitalName, Integer patientRoomCount, Integer totalNumberOfBeds, String businessTypeName, Integer businessStatusCode, Float totalAreaSize) {
        this.id = id;
        this.roadNameAddress = roadNameAddress;
        this.hospitalName = hospitalName;
        this.patientRoomCount = patientRoomCount;
        this.totalNumberOfBeds = totalNumberOfBeds;
        this.businessTypeName = businessTypeName;
        this.businessStatusCode = businessStatusCode;
        this.totalAreaSize = totalAreaSize;
    }
}
