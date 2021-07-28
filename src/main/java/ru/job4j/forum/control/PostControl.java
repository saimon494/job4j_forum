package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.service.MessageService;
import ru.job4j.forum.service.PostService;

@Controller
public class PostControl {

    private final PostService postService;
    private final MessageService messageService;

    public PostControl(PostService postService, MessageService messageService) {
        this.postService = postService;
        this.messageService = messageService;
    }

    @GetMapping("/post")
    public String postPage(@RequestParam int postId, Model model) {
        model.addAttribute("post", postService.findById(postId));
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "post";
    }

    @PostMapping("/message/create")
    public String createMessage(@ModelAttribute Message message,
                                @RequestParam int postId) {
        messageService.save(message, postId);
        return "redirect:/post?postId=" + postId;
    }
}
