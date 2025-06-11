package com.jktech.dto;

import lombok.Data;

@Data
public class QAResponse {
    private String title;
    private String snippet;

    public QAResponse(String title, String snippet) {
        this.title = title;
        this.snippet = snippet;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }
}

