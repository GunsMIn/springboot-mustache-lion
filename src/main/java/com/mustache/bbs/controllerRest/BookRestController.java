package com.mustache.bbs.controllerRest;

import com.mustache.bbs.domain.dto.bookDto.BookListDto;
import com.mustache.bbs.repository.BookRepository;
import com.mustache.bbs.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@Slf4j
public class BookRestController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookListDto>> getList() {
        List<BookListDto> bookListDtoList = bookService.findAll();
        return ResponseEntity.ok().body(bookListDtoList);
    }
}
