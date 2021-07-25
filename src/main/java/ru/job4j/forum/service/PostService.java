package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.PostRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public Collection<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(int id) {
        return postRepository.findById(id);
    }

    public void createPost(HttpServletRequest request, Post post) {
        if (post.getId() != 0)  {
            postRepository.updateNameDesc(post);
        } else {
            post.setAuthor((User) request.getSession().getAttribute("user"));
            post.setCreated(GregorianCalendar.getInstance());
            save(post);
        }
    }
}
