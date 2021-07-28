package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

@Controller
public class EditControl {

    private final PostService postService;
    private final UserService userService;

    public EditControl(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/post/edit")
    public String editPage(@RequestParam(required = false) Integer postId,
                           Model model) {
        if (postId != null) {
            var user = userService.findByUsername(
                    SecurityContextHolder.getContext().getAuthentication().getName());
            var post = postService.findById(postId);
            if (post == null || post.getAuthor().getId() != user.getId()) {
                return "redirect:/index";
            }
            model.addAttribute("post", post);
            model.addAttribute("user",
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        }
        return "post/edit";
    }

    @PostMapping("/post/create")
    public String createPost(@ModelAttribute Post post) {
        postService.createPost(post);
        return "redirect:/index";
    }
}
