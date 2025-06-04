package com.jktech.qnaapplication.qna.service;

import com.jktech.qnaapplication.document.model.Document;
import com.jktech.qnaapplication.document.repository.DocumentRepository;
import com.jktech.qnaapplication.qna.dto.QARequest;
import com.jktech.qnaapplication.qna.dto.QAResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QnAServiceImpl implements QnAService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<QAResponse> getAnswers(QARequest request) {
        String keyword = request.getQuestion();

        List<Document> matchingDocs = documentRepository.findByContentContainingIgnoreCase(keyword);

        // Convert to response
        return matchingDocs.stream()
                .map(doc -> {
                    String snippet = extractSnippet(doc.getContent(), keyword);
                    return new QAResponse(doc.getTitle(), snippet);
                })
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

