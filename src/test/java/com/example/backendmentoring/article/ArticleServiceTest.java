package com.example.backendmentoring.article;

import com.example.backendmentoring.article.application.CreateArticleService;
import com.example.backendmentoring.article.domain.ArticleRepository;
import com.example.backendmentoring.article.dto.ArticleRequestDto;
import com.example.backendmentoring.article.dto.ArticleResponseDto;
import com.example.backendmentoring.user.User;
import com.example.backendmentoring.user.UserReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private CreateArticleService createArticleService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private UserReader userReader;

    @DisplayName("게시글 생성에 성공한다.")
    @Test
    void createArticle() {
        // given
        ArticleRequestDto requestDto = new ArticleRequestDto("제목", "내용");
        String userEmail = "test@test.com";
        User user = User.of(userEmail, "testuser", "password");

        when(userReader.getByEmail(userEmail)).thenReturn(user);
        when(articleRepository.save(any(Article.class))).thenAnswer(invocation -> {
            Article article = invocation.getArgument(0);
            ReflectionTestUtils.setField(article, "id", 1L); // ID를 할당하는 로직 추가
            return article;
        });

        // when
        ArticleResponseDto responseDto = createArticleService.createArticle(requestDto, userEmail);

        // then
        assertThat(responseDto.getId()).isEqualTo(1L);
        assertThat(responseDto.getTitle()).isEqualTo("제목");
        assertThat(responseDto.getContent()).isEqualTo("내용");
        assertThat(responseDto.getWriterEmail()).isEqualTo(userEmail);
    }
}
