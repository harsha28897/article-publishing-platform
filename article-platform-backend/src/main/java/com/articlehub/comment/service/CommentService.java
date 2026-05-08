package com.articlehub.comment.service;

import com.articlehub.article.entity.Article;
import com.articlehub.article.repository.ArticleRepository;
import com.articlehub.comment.dto.CommentResponse;
import com.articlehub.comment.dto.CreateCommentRequest;
import com.articlehub.comment.dto.ReplyCommentRequest;
import com.articlehub.comment.entity.Comment;
import com.articlehub.comment.mapper.CommentMapper;
import com.articlehub.comment.repository.CommentRepository;
import com.articlehub.exception.custom.ResourceNotFoundException;
import com.articlehub.user.entity.User;
import com.articlehub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final ArticleRepository articleRepository;

    private final UserRepository userRepository;

    public CommentResponse addComment(Long articleId, CreateCommentRequest request) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User author = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Article not found"));

        Comment comment = new Comment();

        comment.setContent(request.getContent());

        comment.setAuthor(author);

        comment.setArticle(article);

        Comment savedComment = commentRepository.save(comment);

        return CommentMapper.toResponse(savedComment);
    }

    public CommentResponse replyToComment(Long commentId, ReplyCommentRequest request) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User author = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("user not found"));

        Comment parentComment = commentRepository.findById(commentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment not found"));

        Comment reply = new Comment();

        reply.setContent(request.getContent());

        reply.setAuthor(author);

        reply.setArticle(parentComment.getArticle());

        reply.setParentComment(parentComment);

        Comment savedReply = commentRepository.save(reply);

        return CommentMapper.toResponse(savedReply);
    }

    public List<CommentResponse> getArticleComments(Long articleId) {

        return commentRepository
                .findByArticleIdAndParentCommentIsNull(articleId)
                .stream()
                .map(CommentMapper::toResponse)
                .toList();
    }
}
