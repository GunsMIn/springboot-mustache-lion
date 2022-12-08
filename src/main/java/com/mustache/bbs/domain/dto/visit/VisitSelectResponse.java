package com.mustache.bbs.domain.dto.visit;


import com.mustache.bbs.domain.entity.Disease;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.domain.entity.Visit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data  @NoArgsConstructor
public class VisitSelectResponse {

    private String hospitalName;

    private String visitTime;

    private String userName;

    private Long userId;

    private String diseaseName;

    private Integer count;



    public VisitSelectResponse(Visit visit) {

        this.userName = visit.getUser().getUsername();
        this.userId = visit.getUser().getId();
        this.hospitalName = visit.getHospital().getHospitalName();
        this.diseaseName = visit.getDisease().getName();
        this.count = visit.getCount();
        this.visitTime = visit.getCreatedDate();
    }

}
