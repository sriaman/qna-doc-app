package com.jktech;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jktech.dto.QARequest;
import com.jktech.dto.QAResponse;
import com.jktech.service.QnAService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest(classes = QnaApplication.class)
public class QnAControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QnAService qnaService;

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser(username = "testuser", roles = {"VIEWER"})
    @Test
    public void testAskQuestion() throws Exception {
        QARequest request = new QARequest();
        request.setQuestion("What is Java?");
        List<QAResponse> mockAnswers = List.of(new QAResponse("Java", "Java is a programming language."));
        mockMvc.perform(post("/api/qa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}