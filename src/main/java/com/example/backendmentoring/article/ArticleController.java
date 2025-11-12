package com.example.backendmentoring.article;

import com.example.backendmentoring.article.dto.ArticleRequestDto;
import com.example.backendmentoring.article.dto.ArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid; // 추가

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleResponseDto> createArticle(
            @Valid @RequestBody final ArticleRequestDto requestDto, // @Valid 추가
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        ArticleResponseDto responseDto = articleService.createArticle(requestDto, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
