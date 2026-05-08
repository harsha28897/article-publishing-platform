package com.articlehub.like.entity;

import com.articlehub.article.entity.Article;
import com.articlehub.common.entity.BaseEntity;
import com.articlehub.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "article_likes",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "article_id",
                                "user_id"
                        }
                )
        }
)
public class ArticleLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
