package com.mustache.bbs.controller;

import com.mustache.bbs.controllerRest.ArticleRestController;
import com.mustache.bbs.domain.dto.articleAdd.ArticleAddRequestDto;
import com.mustache.bbs.domain.dto.articleAdd.ArticleAddResponseDto;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
​

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        ArticleAddResponseDto articleDto = ArticleAddResponseDto.builder()
                .id(1L)
                .title("안녕하세요")
                .content("첫 테스트 글입니다")
                .build();

        given(articleService.getArticle(1L)).willReturn(articleDto);
        Long articleId = 1L;

        String url = String.format("/api/articles/%d", articleId);
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("안녕하세요"))
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").value("첫 테스트 글입니다"))
                .andDo(print());


        verify(articleService).getArticle(articleId);
    }

    @Test
    @DisplayName("add Article")
    void add() throws Exception{
        //given
        String title = "Test title";
        String content = "Test content";
        ArticleAddRequestDto articleReq = new ArticleAddRequestDto(title,content);


        given(articleService.addArticle(articleReq))
                .willReturn(new ArticleAddResponseDto(8L, title, content));

            mockMvc.perform(post("api/articles"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content)
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content))
            .andExpect(jsonPath("$.content").exists());

        verify(articleService.addArticle(new ArticleAddRequestDto(title, content)));

    }


}