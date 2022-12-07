package com.mustache.bbs.controllerRest;

import com.mustache.bbs.domain.dto.visit.VisitCreateRequest;
import com.mustache.bbs.domain.dto.visit.VisitCreateResponse;
import com.mustache.bbs.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class VisitController {

    private final VisitService visitService;

    @PostMapping("/api/v1/create")
    public ResponseEntity<VisitCreateResponse> create(@RequestBody VisitCreateRequest visitCreateRequest, Authentication authentication) {
        log.info("Controller user:{},authentication :{}", authentication.getName(),authentication.isAuthenticated());
        VisitCreateResponse visitCreateResponse = visitService.create(visitCreateRequest);
        return ResponseEntity.ok().body(visitCreateResponse);
    }

}
