package com.mustache.bbs.controller;

import com.mustache.bbs.domain.entity.Comment;
import com.mustache.bbs.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentApiController {

    private final CommentService commentService;

    //댓글 생성
    @PostMapping("/comments/{id}")
    public ResponseEntity registerComment(@PathVariable Long id, @RequestBody Comment comment) {
        log.info("여기까지 들어옴 !!! id{}",id);

        return ResponseEntity.ok(commentService.commentSave(id, comment));
    }
}
