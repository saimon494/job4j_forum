package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.util.*;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();
    private int lastId = 0;
    private final UserService userService;

    public PostService(UserService userService) {
        this.userService = userService;
        Post post = new Post();
        post.setName("Правила форума");
        post.setDesc("Обязательно к прочтению");
        post.setCreated(new GregorianCalendar(2021, Calendar.JULY, 27));
        post.setAuthor(userService.findByName("admin"));
        save(post);
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            create(post);
        } else {
            update(post);
        }
    }

    private void create(Post post) {
        post.setId(++lastId);
        posts.add(post);
    }

    private void update(Post post) {
        int id = posts.indexOf(post);
        if (id != -1) {
            posts.set(id, post);
        }
    }

    public void updateNameDesc(Post post) {
        Optional.of(findById(post.getId())).ifPresent(
                post1 -> {
                    post1.setName(post.getName());
                    post1.setDesc(post.getDesc());
                }
        );
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post findById(int id) {
        return posts.stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
