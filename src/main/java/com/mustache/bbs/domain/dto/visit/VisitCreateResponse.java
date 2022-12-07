package com.mustache.bbs.domain.dto.visit;

import com.mustache.bbs.domain.entity.Disease;
import lombok.*;

@Data
@AllArgsConstructor
@Getter
@NoArgsConstructor @Builder
public class VisitCreateResponse {
    private Integer hospitalId;
    private Disease disease;
    private Integer count;


}

