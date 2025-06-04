package com.jktech.service;

import com.jktech.client.DocumentFeignClient;
import com.jktech.dto.QARequest;
import com.jktech.model.Document;
import com.jktech.repository.DocumentRepository;
import com.jktech.dto.QAResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QnAServiceImpl implements QnAService {

    @Autowired
    private DocumentRepository documentRepository;

    private final DocumentFeignClient documentClient;

    public QnAServiceImpl(DocumentFeignClient documentClient) {
        this.documentClient = documentClient;
    }

    @Override
    @Cacheable(value = "qna", key = "#request.question")
    public List<QAResponse> getAnswers(QARequest request) {
        String keyword = request.getQuestion();

        List<Document> matchingDocs = documentRepository.findByContentContainingIgnoreCase(keyword);

        return matchingDocs.stream()
                .map(doc -> {
                    String snippet = extractSnippet(doc.getContent(), keyword);
                    return new QAResponse(doc.getTitle(), snippet);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<QAResponse> getAnswers(String question, int page, int size) {
        return documentClient.searchDocuments(question, page, size)
                .stream()
                .map(doc -> new QAResponse(doc.getTitle(), doc.getSnippet()))
                .collect(Collectors.toList());
    }

    private String extractSnippet(String content, String keyword) {
        int index = content.toLowerCase().indexOf(keyword.toLowerCase());
        if (index == -1) return content.length() > 200 ? content.substring(0, 200) + "..." : content;

        int start = Math.max(0, index - 50);
        int end = Math.min(content.length(), index + keyword.length() + 50);
        return content.substring(start, end) + "...";
    }
}

