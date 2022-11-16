package com.mustache.bbs.controller;

import com.mustache.bbs.controllerRest.ArticleRestController;
import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;


    @Test
    @DisplayName("Aricle restController get")
    void get() throws Exception{
        //{"id":1,"title":"안녕하세요 ","content":"첫 테스트 글입니다"}
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .title("안녕하세요")
                .content("첫 테스트 글입니다")
                .build();

        given(articleService.getArticle(1L)).willReturn(articleDto);
    }

}