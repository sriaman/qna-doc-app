package com.jktech.controller;

import com.jktech.model.DocumentFileResponse;
import com.jktech.dto.DocumentRequest;
import com.jktech.model.Document;
import com.jktech.model.DocumentFile;
import com.jktech.model.DocumentResponse;
import com.jktech.service.DocumentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public ResponseEntity<DocumentFileResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            DocumentFile savedDocument = documentService.storeFile(file);

            DocumentFileResponse response = new DocumentFileResponse();
            response.setMessage("File uploaded successfully");
            response.setDocumentId(savedDocument.getId());
            response.setFileName(savedDocument.getFilename());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<DocumentResponse>> searchDocuments(@RequestParam("keyword") String keyword, @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        List<DocumentResponse> results = documentService.searchDocuments(keyword, page, size);
        return ResponseEntity.ok(results);
    }

    @GetMapping
    public ResponseEntity<List<Document>> getDocuments() {
        return ResponseEntity.ok(documentService.retrieveAll());
    }
}
