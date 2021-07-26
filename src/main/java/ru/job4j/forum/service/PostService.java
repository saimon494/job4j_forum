package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.PostCrudRep;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class PostService {

    private final PostCrudRep postCrudRep;

    public PostService(PostCrudRep postCrudRep) {
        this.postCrudRep = postCrudRep;
    }

    @Transactional
    public void save(Post post) {
        postCrudRep.save(post);
    }

    @Transactional
    public List<Post> findAll() {
        List<Post> rsl = new ArrayList<>();
        postCrudRep.findAll().forEach(rsl::add);
        rsl.sort(Comparator.comparing(Post::getId));
        return rsl;
    }

    public Post findById(int id) {
        return postCrudRep.findById(id).orElse(null);
    }

    @Transactional
    public void createPost(HttpServletRequest request, Post post) {
        if (post.getId() != 0)  {
            postCrudRep.updateNameDesc(post.getId(), post.getName(), post.getDescription());
        } else {
            post.setAuthor((User) request.getSession().getAttribute("user"));
            post.setCreated(GregorianCalendar.getInstance());
            save(post);
        }
    }
}
