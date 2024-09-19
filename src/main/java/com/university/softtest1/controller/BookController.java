package com.university.softtest1.controller;


import com.university.softtest1.DTO.BookDTO;
import com.university.softtest1.DTO.GenreDTO;
import com.university.softtest1.entity.BookEntity;
import com.university.softtest1.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    private BookDTO convertToDTO(BookEntity bookEntity) {
        BookDTO dto = new BookDTO();
        dto.setId(bookEntity.getId());
        dto.setName(bookEntity.getName());
        dto.setAuthor(bookEntity.getAuthor());
        dto.setPageCount(bookEntity.getPageCount());
        dto.setReleaseYear(bookEntity.getReleaseYear());
        dto.setGenres(bookEntity.getGenres().stream()
                .map(genre -> {
                    GenreDTO genreDTO = new GenreDTO();
                    genreDTO.setId(genre.getId());
                    genreDTO.setName(genre.getName());
                    return genreDTO;
                })
                .collect(Collectors.toSet()));
        return dto;
    }


    // Select * books
    @GetMapping("/books")
    public List<BookEntity> getAllBooks() {
        return bookService.getAllBooks();
    }


//    @GetMapping("/books")
//    public List<BookDTO> getAllBooks() {
//        List<BookEntity> books = bookService.getAllBooks();
//        return books.stream().map(this::convertToDTO).collect(Collectors.toList());
//    }

    // Select book by id
    @GetMapping("/books/{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable Long id) {
        Optional<BookEntity> book = Optional.ofNullable(bookService.getBookById(id));
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*@GetMapping("/books/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        BookEntity book = bookService.getBookById(id);
        return convertToDTO(book);
    }*/

    // Select book name by id
    @GetMapping("/books/{id}/name")
    public ResponseEntity<String> getBookNameById(@PathVariable Long id) {
        Optional<BookEntity> book = Optional.ofNullable(bookService.getBookById(id));
        return book.map(bookEntity -> ResponseEntity.ok(bookEntity.getName()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Select books by author
    @GetMapping("/books/authorsearch")
    public List<BookEntity> getBooksByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }

    // Select books by genre
    @GetMapping("/books/genresearch")
    public List<BookEntity> getBooksByGenre(@RequestParam String genre) {
        return bookService.getBooksByGenre(genre);
    }

    // Create a new book
    @PostMapping("/books/create")
    public BookEntity createBook(@RequestBody BookEntity bookEntity) {
        return bookService.createBook(bookEntity);
    }

    /*@PostMapping("/books/create")
    public BookDTO createBook(@RequestBody BookEntity bookEntity) {
        BookEntity createdBook = bookService.createBook(bookEntity);
        return convertToDTO(createdBook);
    }*/

    @PutMapping("/books/update/{id}")
    public ResponseEntity<BookEntity> updateBook(@PathVariable Long id, @RequestBody BookEntity bookEntity) {
        try {
            BookEntity updatedBook = bookService.updateBook(id, bookEntity);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    // Delete a book by its ID
    @DeleteMapping("/books/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }




}
