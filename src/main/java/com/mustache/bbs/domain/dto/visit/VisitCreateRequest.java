package com.mustache.bbs.domain.dto.visit;

import com.mustache.bbs.domain.entity.Disease;
import lombok.Data;
import lombok.Getter;

@Data @Getter
public class VisitCreateRequest {
    private Integer hospitalId;
    private Disease disease;
    private Integer count;
}
