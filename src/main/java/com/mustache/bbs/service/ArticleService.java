package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.articleAdd.ArticleAddResponseDto;
import com.mustache.bbs.domain.dto.articleAdd.ArticleAddRequestDto;
import com.mustache.bbs.domain.dto.articleDelte.ArticleDeleteReqDto;
import com.mustache.bbs.domain.dto.articleDelte.ArticleDeleteResponseDto;
import com.mustache.bbs.domain.dto.articleListDto.ArticleListDto;
import com.mustache.bbs.domain.dto.articleSelect.ArticleResponseDto;
import com.mustache.bbs.domain.dto.articleUpdate.ArticleUpdateReqDto;
import com.mustache.bbs.domain.dto.articleUpdate.ArticleUpdateResponseDto;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    //한개의 게시글을 조회하는 기능
    @Transactional(readOnly = true)
    public ArticleResponseDto getArticle(Long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        Article article = articleOptional.orElse(new Article());
        ArticleResponseDto articleDto = Article.transSelectDto(article);
        return articleDto;
    }

    //add
    public ArticleAddResponseDto addArticle(ArticleAddRequestDto articleRequestDto) {
        Article article = articleRequestDto.toEntity(articleRequestDto);
        //save를 할때는 JpaRepository<Article,Long>를 사용해야 하기때문에
        //articleRequestDto -> 를 Article 타입으로 바꿔줘야한다.
        Article savedArticle = articleRepository.save(article);

        ArticleAddResponseDto articleDto = Article.transDto(savedArticle);
        return articleDto;
    }

    //update
    public ArticleUpdateResponseDto updateArticle(Long id, ArticleUpdateReqDto articleUpdateReqDto) {
        Optional<Article> findArticle = articleRepository.findById(id);
        Article originArticle = findArticle.orElse(new Article());

        //변경감지 사용할 것 (dirty cash)
        originArticle.setTitle(articleUpdateReqDto.getTitle());
        originArticle.setContent(articleUpdateReqDto.getContent());
        // 여기서 변경이 됨
        ArticleUpdateResponseDto articleUpdateResponseDto = Article.transUpdateDto(originArticle);
        return articleUpdateResponseDto;
    }

    //delete
    public ArticleDeleteResponseDto deleteArticle(Long id) {
        Optional<Article> findArticle = articleRepository.findById(id);
        Article originArticle = findArticle.orElse(new Article()); // 영속성 컨텍스트에서 엔티티 꺼내옴

        articleRepository.delete(originArticle);
        ArticleDeleteResponseDto articleDeleteResponseDto = Article.transDeleteDto(originArticle);
        return articleDeleteResponseDto;
    }

    //selectAll
    public List<ArticleListDto> selectAll() {
        List<Article> articleList = articleRepository.findAll();
        //컬렉션 타입은 stream 사용가능
        List<ArticleListDto> ArticleListDtoList = articleList.stream()
                .map(a -> new ArticleListDto(a.getId(), a.getTitle(), a.getContent()))
                .collect(Collectors.toList());
        return ArticleListDtoList;
    }
}
