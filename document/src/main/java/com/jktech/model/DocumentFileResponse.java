package com.jktech.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentFileResponse {
    private String message;
    private Long documentId;
    private String fileName;

    public DocumentFileResponse() {
    }

    public DocumentFileResponse(String message, Long documentId, String fileName) {
        this.message = message;
        this.documentId = documentId;
        this.fileName = fileName;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return '{' +
                "\"message\":\""  +message + '\"' +
                ",\"documentId\":"  + documentId +
                ",\"fileName\":" +'\"' + fileName + '\"' +
                '}';
    }
}
