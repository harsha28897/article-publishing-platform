CREATE TABLE article_likes
(
    id BIGSERIAL PRIMARY KEY,

    article_id BIGINT NOT NULL,

    user_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT fk_like_article
        FOREIGN KEY (article_id)
        REFERENCES articles(id),

    CONSTRAINT fk_like_user
        FOREIGN KEY (user_id)
        REFERENCES users(id),

    CONSTRAINT uk_article_user_like
        UNIQUE (article_id, user_id)
);