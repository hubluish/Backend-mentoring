package com.example.backendmentoring.article;

import com.example.backendmentoring.user.User;
import com.example.backendmentoring.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.save(User.of("test@test.com", "testuser", "password"));
    }

    @DisplayName("게시글 생성 API 호출에 성공한다.")
    @Test
    @WithMockUser(username = "test@test.com", roles = "USER")
    void createArticle() throws Exception {
        // given
        String url = "/api/articles";
        String json = objectMapper.writeValueAsString(new com.example.backendmentoring.article.dto.ArticleRequestDto("제목", "내용"));

        // when & then
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.content").value("내용"))
                .andExpect(jsonPath("$.writerEmail").value("test@test.com"));
    }

    @DisplayName("제목이 비어있으면 게시글 생성에 실패한다.")
    @Test
    @WithMockUser(username = "test@test.com", roles = "USER")
    void createArticle_withEmptyTitle() throws Exception {
        // given
        String url = "/api/articles";
        String json = objectMapper.writeValueAsString(new com.example.backendmentoring.article.dto.ArticleRequestDto("", "내용"));

        // when & then
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
