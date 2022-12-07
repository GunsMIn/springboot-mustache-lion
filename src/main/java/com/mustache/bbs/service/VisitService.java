package com.mustache.bbs.service;

import com.mustache.bbs.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    public String create() {

    }
}
