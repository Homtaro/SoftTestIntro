package com.university.softtest1.repository;

import com.university.softtest1.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository<BookEntity, Long> {

    //Select *
    List<BookEntity> findAll();

    //Select with specific author
    List<BookEntity> findByAuthor(String author);

    //Select * with specific genre

    @Query("SELECT b from BookEntity b JOIN b.genres g WHERE g.name = :genreName")
    List<BookEntity> findBooksByGenre(@Param("genreName") String genreName);

    //Select by ID
    Optional<BookEntity> findById(Long aLong);
}
