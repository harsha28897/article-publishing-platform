package com.articlehub.comment.controller;

import com.articlehub.comment.dto.CommentResponse;
import com.articlehub.comment.dto.CreateCommentRequest;
import com.articlehub.comment.dto.ReplyCommentRequest;
import com.articlehub.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Comments")
@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Add comment to article")
    @PostMapping("/article/{articleId}")
    public CommentResponse addComment(
            @PathVariable Long articleId,
            @Valid @RequestBody CreateCommentRequest request
    ) {

        return commentService.addComment(articleId, request);
    }

    @Operation(summary = "Reply to comment")
    @PostMapping("/{commentId}/reply")
    public CommentResponse replyToComment(
            @PathVariable Long commentId,
            @Valid @RequestBody ReplyCommentRequest request
    ) {

        return commentService.replyToComment(commentId, request);
    }

    @Operation(summary = "Get article comments")
    @GetMapping("/article/{articleId}")
    public List<CommentResponse> getArticleComments(
            @PathVariable Long articleId
    ) {

        return commentService.getArticleComments(articleId);
    }
}
