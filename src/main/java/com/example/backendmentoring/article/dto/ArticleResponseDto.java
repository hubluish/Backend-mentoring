package com.example.backendmentoring.article.dto;

import com.example.backendmentoring.article.Article;
import lombok.Getter;

@Getter
public class ArticleResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String writerEmail;

    public ArticleResponseDto(final Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.writerEmail = article.getUser().getEmail();
    }
}
