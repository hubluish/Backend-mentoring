package com.example.backendmentoring.article.infrastructure;

import com.example.backendmentoring.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleJpaRepository extends JpaRepository<Article, Long> {
}
