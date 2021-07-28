package ru.job4j.forum.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostCrudRep;
import ru.job4j.forum.repository.UserCrudRep;

import java.util.*;

@Service
public class PostService {

    private final PostCrudRep postCrudRep;
    private final UserCrudRep userCrudRep;

    public PostService(PostCrudRep postCrudRep, UserCrudRep userCrudRep) {
        this.postCrudRep = postCrudRep;
        this.userCrudRep = userCrudRep;
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
    public void createPost(Post post) {
        if (post.getId() != 0)  {
            postCrudRep.updateNameDesc(post.getId(), post.getName(), post.getDescription());
        } else {
            post.setAuthor(userCrudRep.findByUsername(
                    SecurityContextHolder.getContext().getAuthentication().getName()));
            post.setCreated(GregorianCalendar.getInstance());
            save(post);
        }
    }
}
