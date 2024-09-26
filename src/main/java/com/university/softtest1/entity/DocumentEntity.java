package com.university.softtest1.entity;

import com.university.softtest1.model.DocType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "documents")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DocType type;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "signature_date")
    private Date signatureDate;

    @Column(name = "user_login", nullable = false)
    private String accessLogin;


}
