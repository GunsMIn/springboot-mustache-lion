package com.mustache.bbs.controllerRest;

import com.mustache.bbs.domain.dto.bookDto.*;
import com.mustache.bbs.repository.BookRepository;
import com.mustache.bbs.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@Slf4j
public class BookRestController {

    private final BookService bookService;

    //책 count and list
    @GetMapping
    public Result getList() {
        List<BookListDto> bookListDtoList = bookService.findAll();
        Result<List<BookListDto>> listResult = new Result<>(bookListDtoList.size(), bookListDtoList);
        return listResult;
    }

    //bookID -> Book조회
    @GetMapping("/{id}")
    public ResponseEntity<BookSelectDto> getOne(@PathVariable Long id) {
        BookSelectDto bookSelectDto = bookService.findById(id);
        return ResponseEntity.ok().body(bookSelectDto);
    }

    //책 등록
    @PostMapping
    public ResponseEntity<BookCreateResponse> add(@RequestBody BookCreateRequest bookCreateRequest) {
        log.info("controller - > dto :{}",bookCreateRequest);
        BookCreateResponse bookCreateResponse = bookService.addBook(bookCreateRequest);
        return ResponseEntity.ok().body(bookCreateResponse);
    }
    /* {
          "publisherId":1,
          "authorId":100,
          "name":"면접에 합격하는 cs 지식"
        }   */


}
