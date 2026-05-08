package com.articlehub.article.controller;

import com.articlehub.article.dto.ArticleResponse;
import com.articlehub.article.dto.ArticleSummaryResponse;
import com.articlehub.article.dto.CreateArticleRequest;
import com.articlehub.article.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Articles")
@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @Operation(summary = "Create article")
    @PostMapping
    public ArticleResponse createArticle(
            @Valid @RequestBody CreateArticleRequest request
    ) {
        return articleService.createArticle(request);
    }

    @Operation(summary = "Get all articles")
    @GetMapping
    public Page<ArticleSummaryResponse> getAllArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return articleService.getAllArticles(page, size);
    }

    @Operation(summary = "Get article by ID")
    @GetMapping("/{articleId}")
    public ArticleResponse getArticleById(
            @PathVariable Long articleId
    ) {
        return articleService.getArticleById(articleId);
    }
}
