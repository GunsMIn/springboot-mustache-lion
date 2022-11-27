package com.mustache.bbs.controller;

import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import com.mustache.bbs.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final HospitalRepository hospitalRepository;

    @RequestMapping("/")
    public String home(@RequestParam(required = false) String keyword,
                       @RequestParam(required = false) String hospitalName, Pageable pageable, Model model) {
        log.info("home controller");
        Page<Hospital> hospitals;
        if (keyword != null) hospitals=hospitalRepository.findByRoadNameAddressContaining(keyword,pageable);
        else if (hospitalName != null) hospitals=hospitalRepository.findByHospitalNameContaining(hospitalName,pageable);
        else hospitals= hospitalRepository.findAll(pageable);

        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("keyword", keyword);
        model.addAttribute("hospitalName", hospitalName);
        return "hospital/list";
    }
}
