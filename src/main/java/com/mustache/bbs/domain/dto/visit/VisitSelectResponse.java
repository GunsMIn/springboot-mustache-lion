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

    private String userName;

    private Long userId;

    private Disease disease;

    private Integer count;

    public VisitSelectResponse(Visit visit) {
        this.id = visit.getId();
        this.userName = visit.getUser().getUsername();
        this.userId = visit.getUser().getId();
        this.disease = visit.getDisease();
        this.count = visit.getCount();
    }

}
