package com.mustache.bbs.controllerRest;

import com.mustache.bbs.domain.dto.articleAdd.ArticleAddResponseDto;
import com.mustache.bbs.domain.dto.articleAdd.ArticleAddRequestDto;
import com.mustache.bbs.domain.dto.articleDelte.ArticleDeleteResponseDto;
import com.mustache.bbs.domain.dto.articleListDto.ArticleListDto;
import com.mustache.bbs.domain.dto.articleSelect.ArticleResponseDto;
import com.mustache.bbs.domain.dto.articleListDto.Result;
import com.mustache.bbs.domain.dto.articleUpdate.ArticleUpdateResponseDto;
import com.mustache.bbs.domain.dto.articleUpdate.ArticleUpdateReqDto;
import com.mustache.bbs.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ArticleRestController {

    private final ArticleService articleService;

    //단건 조회 컨트롤러
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponseDto> get(@PathVariable Long id) {
        ArticleResponseDto articleDto = articleService.getArticle(id);
        return ResponseEntity.status(HttpStatus.OK).body(articleDto);
    }

    //add 컨트롤러////
    //여기 컨트롤러에서는 dto가 2개가 사용이 될 것 이다. 
    // 1. 하나는 요청 dto  2. 다른 하나는 응답 dto
    // 또 중요한 키 포인트는 JpaRepositroy<Article,Long> 을 사용해야하기 때문에
    // dto에서 엔티티로 변환 해주는 메소드 또한 필요한다
    // 그래서 컨트롤러 매개변수에서는 ArticleAddRequestDto 받고 Service 단에서 Article엔티티로 바꿔준다
    // 그리고 Article 엔티티에서 ArticleAddResponseDto 바꿔주는 메소드를 이용하여 
    // ResponseEntity<ArticleAddResponseDto> 로 반환한다
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleAddResponseDto> add(@RequestBody ArticleAddRequestDto articleRequestDto) {
        log.info("article : {}", articleRequestDto);
        ArticleAddResponseDto articleDto = articleService.addArticle(articleRequestDto);
        return ResponseEntity.ok().body(articleDto);
    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<ArticleUpdateResponseDto> update(@PathVariable Long id,
                                   @RequestBody ArticleUpdateReqDto articleUpdateReqDto) {

        ArticleUpdateResponseDto articleUpdateResponseDto
                = articleService.updateArticle(id, articleUpdateReqDto);

        return ResponseEntity.ok().body(articleUpdateResponseDto);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<ArticleDeleteResponseDto> delete(@PathVariable Long id) {
        ArticleDeleteResponseDto articleDeleteResponseDto = articleService.deleteArticle(id);
        return ResponseEntity.status(HttpStatus.OK).body(articleDeleteResponseDto);
    }

    @GetMapping("/api/articles")
    public Result selectAll() {
        List<ArticleListDto> articleListDtoList = articleService.selectAll();
        //개수 까지 알 수 있다.
        return new Result(articleListDtoList,articleListDtoList.size());
    }
}
