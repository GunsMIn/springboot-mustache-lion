package com.mustache.bbs.controller;


import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.domain.entity.Review;
import com.mustache.bbs.repository.HospitalRepository;

import com.mustache.bbs.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
@Slf4j
public class HospitalController {
    private final HospitalRepository hospitalRepository;
    private final ReviewRepository reviewRepository;

    public HospitalController(HospitalRepository hospitalRepository, ReviewRepository reviewRepository) {
        this.hospitalRepository = hospitalRepository;
        this.reviewRepository = reviewRepository;
    }

    /**
     * @PageableDefault
     * <p>
     * ○ size : 한 페이지에 담을 모델의 수를 정할 수 있다. 기본 값은 10이다.
     * ○ sort : 정렬의 기준이 되는 속성을 정한다.
     * ○ direction : 오름차순과 내림차순 중 기준을 선택할 수 있다.
     * ○ Pageable pageable : PageableDefault 값을 갖고 있는 변수를 선언한다.
     */

    //키워드(지역명) 검색 기능 + 페이징
    @GetMapping
    public String list(@RequestParam(required = false) String keyword,Pageable pageable,Model model)  {
        Page<Hospital> hospitals;
        if(keyword!=null) hospitals = hospitalRepository.findByRoadNameAddressContaining(keyword,pageable);
        else hospitals = hospitalRepository.findAll(pageable);

        log.info("지역명으로 검색한 검색어 : {}",keyword);
        log.info("size:{}", hospitals.getSize());

        model.addAttribute("hospitals", hospitals);
        model.addAttribute("keyword", keyword);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospital/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Hospital> hospital = hospitalRepository.findById(id);
        List<Review> reviews = reviewRepository.findByHospitalId(id);
        log.info("review cnt:{}", reviews.size());
        model.addAttribute("hospital", hospital.get());
        model.addAttribute("reviews", reviews);
        return "hospital/show";
    }

}


