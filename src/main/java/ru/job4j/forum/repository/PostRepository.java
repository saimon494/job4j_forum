package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostRepository {

    private static final AtomicInteger POST_ID = new AtomicInteger(0);
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    public PostRepository() {
        posts.put(1, Post.of("Правила форума", "Обязательно к прочтению",
                User.of("admin"), new GregorianCalendar(2021, Calendar.JULY, 25)));
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public Post findById(int id) {
        return posts.values().stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateNameDesc(Post post) {
        Optional.of(findById(post.getId())).ifPresent(
                post1 -> {
                    post1.setName(post.getName());
                    post1.setDesc(post.getDesc());
                }
        );
    }
}
