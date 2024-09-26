package com.university.softtest1.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name", nullable = false)
    private String name;


    //TODO There can be multiple authors, fix please
    //UPD: NO
    @Column(name = "book_author", nullable = false)
    private String author;

    @Column(name = "book_page_count")
    private Integer pageCount;

    @Column(name = "book_release_year")
    private Integer releaseYear;

    @ManyToMany
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )

    //@JsonManagedReference
    private Set<GenreEntity> genres;

}

