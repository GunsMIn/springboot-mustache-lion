package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.bookDto.BookCreateRequest;
import com.mustache.bbs.domain.dto.bookDto.BookCreateResponse;
import com.mustache.bbs.domain.dto.bookDto.BookListDto;
import com.mustache.bbs.domain.dto.bookDto.BookSelectDto;
import com.mustache.bbs.domain.entity.BookCompany.Author;
import com.mustache.bbs.domain.entity.BookCompany.Book;
import com.mustache.bbs.domain.entity.BookCompany.Publisher;
import com.mustache.bbs.repository.AuthorRepository;
import com.mustache.bbs.repository.BookRepository;
import com.mustache.bbs.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor @Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    //BookList join publisher , author
    public List<BookListDto> findAll() {
        List<Book> books = bookRepository.findAll();
       return books.stream().map(b -> new BookListDto(b))
                .collect(Collectors.toList());
    }

    //bookId -> Book 1건 조회
    public BookSelectDto findById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        Book book = bookOptional.orElseThrow(() -> new RuntimeException("can't find the book"));

        BookSelectDto bookSelectDto = new BookSelectDto(book.getId(), book.getName(),
                book.getPublisher().getName(), book.getPublisher().getAddress(),
                book.getAuthor().getAuthor());
        return bookSelectDto;
    }
    //

    //책 등록
    public BookCreateResponse addBook(BookCreateRequest bookCreateRequest) {
        log.info("service - > dto : {} ", bookCreateRequest);
        //책 해당 출판사
        Optional<Publisher> publisherOptional = publisherRepository.findById(bookCreateRequest.getPublisherId());
        Publisher publisher = publisherOptional.orElseThrow(() -> new RuntimeException("can't find the Publisher"));
        //책 해당 작가
        Optional<Author> authorOptional = authorRepository.findById(bookCreateRequest.getAuthorId());
        Author author = authorOptional.orElseThrow(() -> new RuntimeException("can't find the author"));
        //책
        Book book = Book.builder()
                .name(bookCreateRequest.getName())
                .publisher(publisher)
                .author(author)
                .build();
        //책 등록(insert)
        Book savedBook = bookRepository.save(book);
        //entity - > responseDto
        BookCreateResponse bookCreateResponse = new BookCreateResponse(savedBook);
        bookCreateResponse.setMessage("Book Registration successful");
        return bookCreateResponse;
    }

}
