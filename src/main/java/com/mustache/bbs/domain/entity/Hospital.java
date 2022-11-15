package com.mustache.bbs.domain.entity;

import com.mustache.bbs.domain.dto.HospitalResponse;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Getter
@Table(name = "nation_wide_hospitals")
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
    @Column(name = "total_area_size")
    private Float totalAreaSize;

    // HospitalEntity를 HospitalResponse Dto로 만들어주는 부분
    public static HospitalResponse transDto(Hospital hospital) {
        return new HospitalResponse(hospital.getId(),hospital.getHospitalName(),
                hospital.getRoadNameAddress(),hospital.getPatientRoomCount(),
                hospital.getTotalNumberOfBeds(),hospital.getBusinessTypeName(),
                hospital.getTotalAreaSize());
    }


}
