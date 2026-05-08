package com.articlehub.like.controller;

import com.articlehub.like.dto.LikeResponse;
import com.articlehub.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Likes")
@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @Operation(summary = "Like or unline article")
    @PostMapping("/article/{articleId}")
    public LikeResponse toggleLike(
            @PathVariable Long articleId
    ) {

        return likeService.toggleLike(articleId);
    }

    @Operation(summary = "Get article like count")
    @GetMapping("/article/{articleId}")
    public long getArticleLikes(
            @PathVariable Long articleId
    ) {

        return likeService.getArticleLikes(articleId);
    }
}