CREATE TABLE comments
(
    id BIGSERIAL PRIMARY KEY,

    content TEXT NOT NULL,

    article_id BIGINT NOT NULL,

    author_id BIGINT NOT NULL,

    parent_comment_id BIGINT,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT fk_comment_article
        FOREIGN KEY (article_id)
        REFERENCES articles(id),

    CONSTRAINT fk_comment_author
        FOREIGN KEY (author_id)
        REFERENCES users(id),

    CONSTRAINT fk_parent_comment
        FOREIGN KEY (parent_comment_id)
        REFERENCES comments(id)
);