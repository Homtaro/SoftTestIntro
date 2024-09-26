package com.university.softtest1.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentDTO {

    private Long id;
    private String title;
    private String type;
    private String body;
    private String creationDate;
    private String signatureDate;
    private String accessLogin;


}
