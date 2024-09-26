package com.university.softtest1.service;


import com.university.softtest1.entity.DocumentEntity;
import com.university.softtest1.repository.DocumentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DocumentService {

    private final DocumentRepo documentRepo;

    @Autowired
    public DocumentService(DocumentRepo documentRepo) {
        this.documentRepo = documentRepo;
    }

    //Create a new document
    public DocumentEntity createDocument(DocumentEntity documentEntity) {
        documentEntity.setCreationDate(new Date());
        return documentRepo.save(documentEntity);
    }

    //Find documents by access login
    public Iterable<DocumentEntity> getDocumentsByAccessLogin(String login) {
        return documentRepo.findByAccessLogin(login);
    }

    //Find unsigned documents by access login
    public Iterable<DocumentEntity> getUnsignedDocumentsByAccessLogin(String login) {
        return documentRepo.findBySignatureDateNullAndAccessLogin(login);
    }

    //Find signed documents by access login
    public Iterable<DocumentEntity> getSignedDocumentsByAccessLogin(String login) {
        return documentRepo.findBySignatureDateNotNullAndAccessLogin(login);
    }

    //Find documents in time period
    public Iterable<DocumentEntity> getDocumentsInTimePeriod(Date date_start, Date date_end) {
        return documentRepo.findByCreationDateBetween(date_start, date_end);
    }

    //Update document
    //Question: should creation date be updated as well?
    @Transactional
    public void updateDocument(Long id, DocumentEntity updatedDocument) {
        documentRepo.findById(id)
                .ifPresent(document -> {
                    document.setTitle(updatedDocument.getTitle());
                    document.setType(updatedDocument.getType());
                    document.setBody(updatedDocument.getBody());
                    document.setSignatureDate(updatedDocument.getSignatureDate());
                    document.setAccessLogin(updatedDocument.getAccessLogin());
                    documentRepo.save(document);
                });
    }
    //Delete document
    public void deleteDocument(Long id) {
        documentRepo.deleteById(id);
    }








}
