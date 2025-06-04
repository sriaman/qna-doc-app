package com.jktech.controller;

import com.jktech.dto.QARequest;
import com.jktech.dto.QAResponse;
import com.jktech.service.QnAService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/qa")
public class QnAController {

    @Autowired
    private QnAService qnaService;

    @PostMapping
    public ResponseEntity<List<QAResponse>> askQuestion(@RequestBody QARequest request) {
        return ResponseEntity.ok(qnaService.getAnswers(request));
    }
}
