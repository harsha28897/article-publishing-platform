package com.articlehub.comment.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class CommentResponse {

    private Long id;

    private String content;

    private String authorUsername;

    private LocalDateTime createdAt;

    private List<CommentResponse> replies;
}
