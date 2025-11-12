package com.example.backendmentoring.article;

import com.example.backendmentoring.article.dto.ArticleRequestDto;
import com.example.backendmentoring.article.dto.ArticleResponseDto;
import com.example.backendmentoring.user.User;
import com.example.backendmentoring.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository; // User 정보를 가져오기 위해 추가

    @Transactional
    public ArticleResponseDto createArticle(final ArticleRequestDto requestDto, final String userEmail) {
        // 이메일로 사용자 조회 (인증된 사용자 정보는 userEmail로 넘어온다고 가정)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + userEmail));

        Article article = new Article(requestDto.getTitle(), requestDto.getContent(), user);
        Article savedArticle = articleRepository.save(article);
        return new ArticleResponseDto(savedArticle);
    }
}
