package com.articlehub.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyCommentRequest {

    @NotBlank(message = "Content is required")
    private String content;
}
