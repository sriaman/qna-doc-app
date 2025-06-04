package com.jktech.qnaapplication.document.service;

import com.jktech.qnaapplication.document.dto.DocumentRequest;
import com.jktech.qnaapplication.document.model.Document;
import com.jktech.qnaapplication.document.model.DocumentFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    void ingest(DocumentRequest request);
    List<Document> retrieveAll();
    DocumentFile storeFile(MultipartFile file) throws IOException;
}
