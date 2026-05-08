package com.articlehub.article.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticleSummaryResponse {

    private Long id;

    private String title;

    private String authorUsername;

}
