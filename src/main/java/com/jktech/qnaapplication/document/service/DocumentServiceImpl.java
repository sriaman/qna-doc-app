package com.jktech.qnaapplication.document.service;

import com.jktech.qnaapplication.common.util.AppUtils;
import com.jktech.qnaapplication.document.dto.DocumentRequest;
import com.jktech.qnaapplication.document.model.Document;
import com.jktech.qnaapplication.document.model.DocumentFile;
import com.jktech.qnaapplication.document.repository.DocumentFileRepository;
import com.jktech.qnaapplication.document.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentFileRepository documentFileRepository;

    @Override
    public void ingest(DocumentRequest request) {
        Document document = new Document();
        document.setTitle(request.getTitle());
        document.setAuthor(request.getAuthor());
        document.setType(request.getType());
        System.out.println("Uploading document at " + AppUtils.getCurrentTimestamp());
        document.setUploadDate(LocalDate.now());
        document.setContent(request.getContent());
        documentRepository.save(document);
    }

    @Override
    public DocumentFile storeFile(MultipartFile file) throws IOException {
        DocumentFile doc = new DocumentFile();
        doc.setFilename(file.getOriginalFilename());
        doc.setContentType(file.getContentType());
        doc.setData(file.getBytes()); // Or store path if using filesystem
        return documentFileRepository.save(doc);
    }

    @Override
    public List<Document> retrieveAll() {
        return documentRepository.findAll();
    }
}
