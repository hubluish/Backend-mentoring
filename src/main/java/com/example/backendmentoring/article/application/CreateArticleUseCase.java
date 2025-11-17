package com.example.backendmentoring.article.application;

import com.example.backendmentoring.article.dto.ArticleRequestDto;
import com.example.backendmentoring.article.dto.ArticleResponseDto;

public interface CreateArticleUseCase {

    ArticleResponseDto createArticle(ArticleRequestDto requestDto, String userEmail);
}
