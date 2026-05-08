package com.articlehub.comment.repository;

import com.articlehub.comment.entity.Comment;
import com.articlehub.like.service.LikeService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository
        extends JpaRepository<Comment, Long> {

    List<Comment> findByArticleIdAndParentCommentIsNull(Long articleId);

}
