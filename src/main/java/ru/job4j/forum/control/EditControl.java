package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EditControl {

    private final PostService postService;

    public EditControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/edit")
    public String editPage(HttpServletRequest request,
                           @RequestParam(required = false) Integer postId,
                           Model model) {
        if (postId != null) {
            var user = (User) request.getSession().getAttribute("user");
            var post = postService.findById(postId);
            if (post == null || post.getAuthor().getId() != user.getId()) {
                return "redirect:/index";
            }
            model.addAttribute("post", post);
        }
        return "post/edit";
    }

    @PostMapping("/post/create")
    public String createPost(HttpServletRequest request, @ModelAttribute Post post) {
        postService.createPost(request, post);
        return "redirect:/index";
    }
}
