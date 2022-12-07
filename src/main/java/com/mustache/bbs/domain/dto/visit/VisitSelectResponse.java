package com.mustache.bbs.domain.dto.visit;


import com.mustache.bbs.domain.entity.Disease;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.domain.entity.Visit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data  @NoArgsConstructor
public class VisitSelectResponse {

    private Long id;

    private String hospitalName;

    private Long userId;

    private String userName;

    private String diseaseName;

    private Integer count;

    public VisitSelectResponse(Visit visit) {
        this.id = visit.getId();
        this.userId = visit.getUser().getId();
        this.userName = visit.getUser().getUsername();
        this.hospitalName = visit.getHospital().getHospitalName();
        this.diseaseName = visit.getDisease().getName();
        this.count = visit.getCount();
    }

}
