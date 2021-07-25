package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.service.MessageService;
import ru.job4j.forum.service.PostService;

import javax.servlet.http.HttpServletRequest;

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
        return "post";
    }

    @PostMapping("/message/create")
    public String createMessage(@ModelAttribute Message message,
                                @RequestParam int postId,
                                HttpServletRequest request) {
        messageService.save(message, postId, request);
        return "redirect:/post?postId=" + postId;
    }
}
