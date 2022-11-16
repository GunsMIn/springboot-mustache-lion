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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        Long articleId = 1L;

        String url = String.format("/api/article/%d", articleId);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists())  // $는 루트 $아래에 hospitalName이 있어야 함
                .andExpect(jsonPath("$.title").value("안녕하세요"))
                .andDo(print()); // http request, response내역을 출력 해라


        verify(articleService).getArticle(articleId);// getHospital()메소드의 호출이 있었는지 확인
    }

}