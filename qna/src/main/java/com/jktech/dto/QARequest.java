package com.jktech.dto;

import lombok.Data;

@Data
public class QARequest {
    private String question;

    public QARequest() {}

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
}
