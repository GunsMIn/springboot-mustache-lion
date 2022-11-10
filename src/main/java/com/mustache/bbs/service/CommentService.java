package com.mustache.bbs.service;

import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.domain.entity.Comment;
import com.mustache.bbs.repository.ArticleRepository;
import com.mustache.bbs.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;


    public Long commentSave(Long id, Comment comment) {
        System.out.println("서비스");
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다."));

        comment.setArticle(article);
        commentRepository.save(comment);
        return comment.getId();
    }



}
