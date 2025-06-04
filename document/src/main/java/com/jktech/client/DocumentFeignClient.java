package com.jktech.client;

import com.jktech.config.FeignClientConfig;
import com.jktech.model.DocumentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "document-service",
        url = "${document.service.url}",
        configuration = FeignClientConfig.class
)
public interface DocumentFeignClient {

    @GetMapping("/api/documents/search")
    List<DocumentResponse> searchDocuments(
            @RequestParam String keyword,
            @RequestParam int page,
            @RequestParam int size
    );

    @GetMapping("/api/documents/{id}")
    DocumentResponse getDocumentById(@PathVariable Long id);
}

