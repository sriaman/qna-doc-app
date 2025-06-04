package com.jktech.service;

import com.jktech.dto.DocumentRequest;
import com.jktech.model.Document;
import com.jktech.model.DocumentFile;
import com.jktech.model.DocumentResponse;
import com.jktech.repository.DocumentFileRepository;
import com.jktech.repository.DocumentRepository;
import com.jktech.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentFileRepository documentFileRepository;

    @Override
    @Cacheable(value = "documents", key = "#keyword")
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
    public List<DocumentResponse> searchDocuments(String keyword, int page, int size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<Document> documentPage = documentRepository.searchByKeyword(keyword, pageable);

        return documentPage.getContent().stream()
                .map(doc -> new DocumentResponse(doc.getId(), doc.getTitle(), getSnippet(doc.getContent(), keyword)))
                .collect(Collectors.toList());
    }

    private String getSnippet(String content, String keyword) {
        int index = content.toLowerCase().indexOf(keyword.toLowerCase());
        if (index == -1) return "";
        int start = Math.max(0, index - 30);
        int end = Math.min(content.length(), index + 30);
        return content.substring(start, end);
    }

    @Override
    public List<Document> retrieveAll() {
        return documentRepository.findAll();
    }
}
