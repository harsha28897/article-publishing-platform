package com.articlehub.article.service;

import com.articlehub.article.dto.ArticleResponse;
import com.articlehub.article.dto.ArticleSummaryResponse;
import com.articlehub.article.dto.CreateArticleRequest;
import com.articlehub.article.entity.Article;
import com.articlehub.article.mapper.ArticleMapper;
import com.articlehub.article.repository.ArticleRepository;
import com.articlehub.exception.custom.ResourceNotFoundException;
import com.articlehub.user.entity.User;
import com.articlehub.user.repository.UserRepository;
import com.articlehub.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final UserRepository userRepository;

    private final TagService tagService;

    public ArticleResponse createArticle(CreateArticleRequest request) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User author = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Article article = new Article();

        article.setTitle(request.getTitle());

        article.setContent(request.getContent());

        article.setAuthor(author);

        Article savedArticle = articleRepository.save(article);

        tagService.processTaggedUsers(
                savedArticle,
                savedArticle.getContent()
        );

        return ArticleMapper.toResponse(savedArticle);
    }

    public Page<ArticleSummaryResponse> getAllArticles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        return articleRepository.findAll(pageable)
                .map(ArticleMapper::toSummaryResponse);
    }

    public ArticleResponse getArticleById(Long articleId) {

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Article not found"));

        return ArticleMapper.toResponse(article);
    }
}
