package com.jktech.qnaapplication.qna.service;

import com.jktech.qnaapplication.qna.dto.QARequest;
import com.jktech.qnaapplication.qna.dto.QAResponse;

import java.util.List;

public interface QnAService {
    List<QAResponse> getAnswers(QARequest qaRequest);
}
