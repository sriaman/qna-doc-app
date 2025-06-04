package com.jktech.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class DocumentResponse {

    private Long id;
    private String title;
    private String snippet;

    public DocumentResponse(Long id, String title, String snippet) {
        this.id = id;
        this.title = title;
        this.snippet = snippet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
