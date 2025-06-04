package com.jktech.qnaapplication.document.controller;

import com.jktech.qnaapplication.document.dto.DocumentRequest;
import com.jktech.qnaapplication.document.model.Document;
import com.jktech.qnaapplication.document.model.DocumentFile;
import com.jktech.qnaapplication.document.service.DocumentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestBody DocumentRequest request) {
        documentService.ingest(request);
        return ResponseEntity.ok("Document uploaded");
    }

    @PostMapping("/upload-file")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            DocumentFile savedDocument = documentService.storeFile(file);
            return ResponseEntity.ok(Map.of(
                    "message", "File uploaded successfully",
                    "documentId", savedDocument.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Document>> getDocuments() {
        return ResponseEntity.ok(documentService.retrieveAll());
    }
}
