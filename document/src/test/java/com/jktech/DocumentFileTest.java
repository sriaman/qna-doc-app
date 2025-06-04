package com.jktech;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jktech.model.DocumentFileResponse;
import com.jktech.service.DocumentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DocumentFileTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser(username = "testuser", roles = {"VIEWER"})
    @Test
    public void testUploadDocument_withMockUser() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile(
                "file", "test.txt", MediaType.TEXT_PLAIN_VALUE, "Test content".getBytes());

        mockMvc.perform(multipart("/api/documents/upload-file")
                        .file(mockFile))
                .andExpect(status().isOk())
                .andExpect(content().string(new DocumentFileResponse("File uploaded successfully",1L,"test.txt").toString()));
    }

}
