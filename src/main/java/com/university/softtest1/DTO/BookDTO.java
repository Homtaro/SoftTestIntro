package com.university.softtest1.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BookDTO {

    private Long id;
    private String name;
    private String author;
    private Integer pageCount;
    private Integer releaseYear;
    private Set<GenreDTO> genres;

}
