package com.jktech.service;

import com.jktech.dto.QARequest;
import com.jktech.dto.QAResponse;

import java.util.List;

public interface QnAService {
    List<QAResponse> getAnswers(QARequest qaRequest);
    List<QAResponse> getAnswers(String question, int page, int size);
}
