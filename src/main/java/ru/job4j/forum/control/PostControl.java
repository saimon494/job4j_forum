package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.MessageService;
import ru.job4j.forum.service.PostService;

import javax.servlet.http.HttpServletRequest;
import java.util.GregorianCalendar;

@Controller
public class PostControl {

    private PostService posts;
    private MessageService messages;

    public PostControl(PostService posts, MessageService messages) {
        this.posts = posts;
        this.messages = messages;
    }

    @GetMapping("/post")
    public String postPage(@RequestParam int postId, Model model) {
        model.addAttribute("post", posts.findById(postId));
        return "post";
    }

    @PostMapping("/message/create")
    public String createMessage(@ModelAttribute Message message,
                                @RequestParam int postId,
                                HttpServletRequest request) {
        var user = (User) request.getSession().getAttribute("user");
        message.setAuthor(user);
        message.setCreated(GregorianCalendar.getInstance());
        messages.save(message);
        posts.findById(postId).addMessage(message);
        return "redirect:/post?postId=" + postId;
    }
}
