package com.example.backendmentoring.article.domain;

import com.example.backendmentoring.article.Article;

public interface ArticleRepository {

    Article save(Article article);
}
