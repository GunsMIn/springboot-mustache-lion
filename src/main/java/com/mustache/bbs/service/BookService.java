package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.bookDto.BookListDto;
import com.mustache.bbs.domain.dto.bookDto.BookSelectDto;
import com.mustache.bbs.domain.entity.BookCompany.Book;
import com.mustache.bbs.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    //BookList join publisher , author
    public List<BookListDto> findAll() {
        List<Book> books = bookRepository.findAll();
       return books.stream().map(b -> new BookListDto(b))
                .collect(Collectors.toList());
    }

    //bookId -> Book
    public BookSelectDto findById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        Book book = bookOptional.orElseThrow(() -> new RuntimeException("해당 BooK을 찾지 못했습니다"));

        BookSelectDto bookSelectDto = new BookSelectDto(book.getId(), book.getName(),
                book.getPublisher().getName(), book.getPublisher().getAddress(),
                book.getAuthor().getAuthor());
        return bookSelectDto;
    }

}
