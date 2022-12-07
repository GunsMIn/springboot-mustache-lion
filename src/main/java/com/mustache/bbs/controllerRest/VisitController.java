package com.mustache.bbs.controllerRest;

import com.mustache.bbs.domain.Response;
import com.mustache.bbs.domain.dto.visit.VisitCreateRequest;
import com.mustache.bbs.domain.dto.visit.VisitCreateResponse;
import com.mustache.bbs.domain.dto.visit.VisitSelectResponse;
import com.mustache.bbs.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class VisitController {
    //http://localhost:8909/api/v1/create/김건우
    // "hospitalId":"2",
    //  "disease" :"B",
    //  "count":"4"
    private final VisitService visitService;

    @PostMapping("/api/v1/create/{userName}")
    public ResponseEntity<VisitCreateResponse> create(@RequestBody VisitCreateRequest visitCreateRequest, @PathVariable  String userName, Authentication authentication) {
        log.info("Controller user:{},authentication :{}", authentication.getName(),authentication.isAuthenticated());
        VisitCreateResponse visitCreateResponse = visitService.create(visitCreateRequest,userName);
        return ResponseEntity.ok().body(visitCreateResponse);
    }

    @GetMapping("/api/v1/users/{id}")
    public Response<VisitSelectResponse> get(@PathVariable Long id) {
        VisitSelectResponse visitServiceOne = visitService.getOne(id);
        return Response.success(visitServiceOne);
    }

}
