package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.bookDto.BookListDto;
import com.mustache.bbs.domain.entity.BookCompany.Book;
import com.mustache.bbs.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookListDto> findAll() {
        List<Book> books = bookRepository.findAll();
       return books.stream().map(b -> new BookListDto(b))
                .collect(Collectors.toList());
    }

}
