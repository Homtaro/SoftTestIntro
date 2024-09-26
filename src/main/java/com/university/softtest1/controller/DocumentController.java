package com.university.softtest1.controller;

import com.university.softtest1.entity.DocumentEntity;
import com.university.softtest1.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/docs")
public class DocumentController {


    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // Create a new document
    @PostMapping("/create")
    public DocumentEntity createDocument(@RequestBody DocumentEntity documentEntity) {
        return documentService.createDocument(documentEntity);
    }

    // Update document
    @PutMapping("/update/{id}")
    public @ResponseBody ResponseEntity<?> updateDocument(@PathVariable Long id, @RequestBody DocumentEntity updatedDocument) {
        try {
            documentService.updateDocument(id, updatedDocument);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Find documents by access login
    @GetMapping("/search/bylogin")
    public Iterable<DocumentEntity> getDocumentsByAccessLogin(@RequestParam String login) {
        return documentService.getDocumentsByAccessLogin(login);
    }

    // Find unsigned documents by access login
    @GetMapping("/search/unsignedbylogin")
    public Iterable<DocumentEntity> getUnsignedDocumentsByAccessLogin(@RequestParam String login) {
        return documentService.getUnsignedDocumentsByAccessLogin(login);
    }

    // Find signed documents by access login
    @GetMapping("/search/signedbylogin")
    public Iterable<DocumentEntity> getSignedDocumentsByAccessLogin(@RequestParam String login) {
        return documentService.getSignedDocumentsByAccessLogin(login);
    }

    // Find documents in time period
    @GetMapping("/search/bytimeperiod")
    public Iterable<DocumentEntity> getDocumentsInTimePeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date_start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date_end) {
        return documentService.getDocumentsInTimePeriod(date_start, date_end);
    }

    // Delete document
    @DeleteMapping("/delete/{id}")
    public void deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
    }







}
