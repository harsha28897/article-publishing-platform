package com.articlehub.like.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeResponse {

    private boolean liked;

    private long totalLikes;
}
