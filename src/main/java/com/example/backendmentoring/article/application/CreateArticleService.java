package com.example.backendmentoring.article.application;

import com.example.backendmentoring.article.Article;
import com.example.backendmentoring.article.domain.ArticleRepository;
import com.example.backendmentoring.article.dto.ArticleRequestDto;
import com.example.backendmentoring.article.dto.ArticleResponseDto;
import com.example.backendmentoring.user.User;
import com.example.backendmentoring.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateArticleService implements CreateArticleUseCase {

    private final ArticleRepository articleRepository;
    private final UserReader userReader;

    @Override
    @Transactional
    public ArticleResponseDto createArticle(final ArticleRequestDto requestDto, final String userEmail) {
        User writer = userReader.getByEmail(userEmail);
        Article article = new Article(requestDto.getTitle(), requestDto.getContent(), writer);
        Article savedArticle = articleRepository.save(article);
        return new ArticleResponseDto(savedArticle);
    }
}
