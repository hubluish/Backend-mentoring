package com.example.backendmentoring.article.infrastructure;

import com.example.backendmentoring.article.Article;
import com.example.backendmentoring.article.domain.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {

    private final ArticleJpaRepository articleJpaRepository;

    @Override
    public Article save(final Article article) {
        return articleJpaRepository.save(article);
    }
}
