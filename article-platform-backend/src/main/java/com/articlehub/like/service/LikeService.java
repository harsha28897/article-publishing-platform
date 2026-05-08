package com.articlehub.like.service;

import com.articlehub.article.entity.Article;
import com.articlehub.article.repository.ArticleRepository;
import com.articlehub.exception.custom.ResourceNotFoundException;
import com.articlehub.like.dto.LikeResponse;
import com.articlehub.like.entity.ArticleLike;
import com.articlehub.like.repository.ArticleLikeRepository;
import com.articlehub.user.entity.User;
import com.articlehub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final ArticleLikeRepository articleLikeRepository;

    private final ArticleRepository articleRepository;

    private final UserRepository userRepository;

    public LikeResponse toggleLike(Long articleId) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Article not found"));

        return articleLikeRepository
                .findByArticleIdAndUserId(
                        articleId,
                        user.getId()
                )
                .map(existingLike -> {

                    articleLikeRepository.delete(existingLike);

                    long totalLikes =
                            articleLikeRepository
                                    .countByArticleId(articleId);

                    return LikeResponse.builder()
                            .liked(false)
                            .totalLikes(totalLikes)
                            .build();
                })
                .orElseGet(() -> {

                    ArticleLike articleLike =
                            new ArticleLike();

                    articleLike.setArticle(article);

                    articleLike.setUser(user);

                    articleLikeRepository.save(articleLike);

                    long totalLikes =
                            articleLikeRepository
                                    .countByArticleId(articleId);

                    return LikeResponse.builder()
                            .liked(true)
                            .totalLikes(totalLikes)
                            .build();
                });
    }

    public long getArticleLikes(Long articleId) {

        return articleLikeRepository
                .countByArticleId(articleId);
    }
}