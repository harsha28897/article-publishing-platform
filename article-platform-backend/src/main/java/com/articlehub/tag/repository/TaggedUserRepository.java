package com.articlehub.tag.repository;

import com.articlehub.tag.entity.TaggedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaggedUserRepository extends JpaRepository<TaggedUser, Long> {
}
