package com.mustache.bbs.controller;


import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.Review;
import com.mustache.bbs.repository.HospitalRepository;

import com.mustache.bbs.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hospitals")
@RequiredArgsConstructor
@Slf4j
public class HospitalController {
    private final HospitalRepository hospitalRepository;
    private final ReviewRepository reviewRepository;

    //키워드(지역명) 검색 기능 + 페이징
    @GetMapping
    public String list(@RequestParam(required = false) String keyword,
                       @RequestParam(required = false) String hospitalName,
                       Pageable pageable,Model model)  {


        Page<Hospital> hospitals;
        if (hospitalName !=null) {//병원 이름으로 검색시
            hospitals = hospitalRepository.findByHospitalNameContaining(hospitalName, pageable);
            log.info("병원명으로 검색한 검색어 {}",hospitalName);
            log.info("size:{}", hospitals.getSize());
        } else if (keyword != null) {//도로명으로 검색시
            hospitals = hospitalRepository.findByRoadNameAddressContaining(keyword, pageable);
            log.info("지역명으로 검색한 검색어 : {}",keyword);
            log.info("size:{}", hospitals.getSize());
        } else {// 검색X
            hospitals = hospitalRepository.findAll(pageable);
        }



        model.addAttribute("hospitals", hospitals);
        model.addAttribute("keyword", keyword);
        model.addAttribute("hospitalName", hospitalName);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospital/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);
        Hospital hospital = hospitalOptional.get();
        List<Review> reviews = reviewRepository.findByHospitalId(id);
        log.info("review cnt:{}", reviews.size());
        model.addAttribute("hospital", hospital);
        model.addAttribute("reviews", reviews);
        return "hospital/show";
    }

}


