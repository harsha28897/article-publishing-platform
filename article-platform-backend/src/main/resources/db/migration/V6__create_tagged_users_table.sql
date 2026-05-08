CREATE TABLE tagged_users
(
    id BIGSERIAL PRIMARY KEY,

    article_id BIGINT NOT NULL,

    user_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT fk_tagged_article
        FOREIGN KEY (article_id)
        REFERENCES articles(id),

    CONSTRAINT fk_tagged_user
        FOREIGN KEY (user_id)
        REFERENCES users(id),

    CONSTRAINT uk_tagged_article_user
        UNIQUE (article_id, user_id)
);