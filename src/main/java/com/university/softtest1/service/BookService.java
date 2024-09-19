package com.university.softtest1.service;


import com.university.softtest1.entity.BookEntity;
import com.university.softtest1.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo){
        this.bookRepo = bookRepo;
    }

    // Create a new book
    public BookEntity createBook(BookEntity bookEntity) {
        return bookRepo.save(bookEntity);
    }

    // Select * books
    public List<BookEntity> getAllBooks() {
        return bookRepo.findAll();
    }

    // Select book by id
    public BookEntity getBookById(Long id) {
        return bookRepo.findById(id).orElse(null);
    }

    // Select book name by id
    public String getBookNameById(Long id) {
        return bookRepo.findById(id).map(BookEntity::getName).orElse("Book not found");
    }

    // Select books by author
    public List<BookEntity> getBooksByAuthor(String author) {
        return bookRepo.findByAuthor(author);
    }

    // Select books by genre
    public List<BookEntity> getBooksByGenre(String genre) {
        return bookRepo.findBooksByGenre(genre);
    }

    // Update book by id
    public BookEntity updateBook(Long id, BookEntity updatedBook) {
        return bookRepo.findById(id)
                .map(book -> {
                    book.setName(updatedBook.getName());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setPageCount(updatedBook.getPageCount());
                    book.setReleaseYear(updatedBook.getReleaseYear());
                    book.setGenres(updatedBook.getGenres());
                    return bookRepo.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Book not found with id " + id));
    }

    // Delete book by id
    public void deleteBook(Long id) {
        if (bookRepo.existsById(id)) {
            bookRepo.deleteById(id);
        } else {
            throw new RuntimeException("Book not found with id " + id);
        }
    }





}
