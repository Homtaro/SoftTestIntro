package com.university.softtest1.repository;

import com.university.softtest1.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepo extends JpaRepository<GenreEntity, Long> {

    //Select *
    List<GenreEntity> findAll();

    //Select by ID
    Optional<GenreEntity> findById(Long aLong);


}
