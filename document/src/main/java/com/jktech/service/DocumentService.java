package com.jktech.service;

import com.jktech.dto.DocumentRequest;
import com.jktech.model.Document;
import com.jktech.model.DocumentFile;
import com.jktech.model.DocumentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    void ingest(DocumentRequest request);
    List<Document> retrieveAll();
    List<DocumentResponse> searchDocuments(String keyword, int page, int size);
    DocumentFile storeFile(MultipartFile file) throws IOException;
}
