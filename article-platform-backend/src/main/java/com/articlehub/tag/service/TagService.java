package com.articlehub.tag.service;

import com.articlehub.article.entity.Article;
import com.articlehub.tag.entity.TaggedUser;
import com.articlehub.tag.repository.TaggedUserRepository;
import com.articlehub.user.entity.User;
import com.articlehub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class TagService {

    private final UserRepository userRepository;

    private final TaggedUserRepository taggedUserRepository;

    private static final Pattern MENTION_PATTERN =
            Pattern.compile("@(\\w+)");

    public void processTaggedUsers(
            Article article,
            String content
    ) {

        Set<String> usernames = extractUsernames(content);

        usernames.forEach(username ->

                userRepository.findByUsername(username)
                        .ifPresent(user -> saveTaggedUser(
                                article,
                                user
                        ))
        );
    }

    private Set<String> extractUsernames(
            String content
    ) {

        Set<String> usernames = new HashSet<>();

        Matcher matcher =
                MENTION_PATTERN.matcher(content);

        while (matcher.find()) {

            usernames.add(matcher.group(1));
        }

        return usernames;
    }

    private void saveTaggedUser(
            Article article,
            User user
    ) {

        TaggedUser taggedUser = new TaggedUser();

        taggedUser.setArticle(article);

        taggedUser.setUser(user);

        taggedUserRepository.save(taggedUser);
    }
}