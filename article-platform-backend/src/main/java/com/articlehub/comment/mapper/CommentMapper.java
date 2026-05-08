package com.articlehub.comment.mapper;

import com.articlehub.comment.dto.CommentResponse;
import com.articlehub.comment.entity.Comment;

import java.util.stream.Collectors;

public class CommentMapper {

    private CommentMapper() {

    }

    public static CommentResponse toResponse(Comment comment) {

        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .authorUsername(comment.getAuthor().getUsername())
                .createdAt(comment.getCreatedAt())
                .replies(comment.getReplies()
                        .stream()
                        .map(CommentMapper::toResponse)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
