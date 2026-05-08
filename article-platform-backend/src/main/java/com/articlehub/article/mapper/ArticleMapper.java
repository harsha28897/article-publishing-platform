package com.articlehub.article.mapper;

import com.articlehub.article.dto.ArticleResponse;
import com.articlehub.article.dto.ArticleSummaryResponse;
import com.articlehub.article.entity.Article;

public class ArticleMapper {

    private ArticleMapper() {

    }

    public static ArticleResponse toResponse(Article article) {

        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .authorUsername(article.getAuthor().getUsername())
                .createdAt(article.getCreatedAt())
                .build();
    }

    public static ArticleSummaryResponse toSummaryResponse(Article article) {

        return ArticleSummaryResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .authorUsername(article.getAuthor().getUsername())
                .build();
    }
}
