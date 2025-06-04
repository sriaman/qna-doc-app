package com.jktech.qnaapplication.qna.controller;

import com.jktech.qnaapplication.qna.dto.QARequest;
import com.jktech.qnaapplication.qna.dto.QAResponse;
import com.jktech.qnaapplication.qna.service.QnAService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
