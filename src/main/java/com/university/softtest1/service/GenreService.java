package com.university.softtest1.service;

import com.university.softtest1.entity.GenreEntity;
import com.university.softtest1.repository.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepo genreRepo;

    @Autowired
    public GenreService(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }


    //Create a new genre
    public GenreEntity createGenre(GenreEntity genreEntity) {
        return genreRepo.save(genreEntity);
    }


    // Select * genres
    public List<GenreEntity> getAllGenres() {
        return genreRepo.findAll();
    }

    // Select genre by id
    public GenreEntity getGenreById(Long id) {
        return genreRepo.findById(id).orElse(null);
    }

    // Select genre name by id
    public String getGenreNameById(Long id) {
        return genreRepo.findById(id).map(GenreEntity::getName).orElse("Genre not found");
    }

    // Update genre by id
    public GenreEntity updateGenre(Long id, GenreEntity updatedGenre) {
        return genreRepo.findById(id)
                .map(genre -> {
                    genre.setName(updatedGenre.getName());
                    return genreRepo.save(genre);
                }).orElse(null);
    }

    // Delete genre by id
    public void deleteGenre(Long id) {
        if (genreRepo.existsById(id)) {
            genreRepo.deleteById(id);
        } else {
            throw new RuntimeException("Book not found with id " + id);
        }
    }



}
