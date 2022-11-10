package com.mustache.bbs.interstalla.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MoneyController {


    @GetMapping("/money")
    public String goPage() {
        return "in";
    }



  /*  @PostMapping("/money")
    public String doRogic(String ){

    }*/
}
