package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.MessageRepository;
import ru.job4j.forum.repository.PostRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.GregorianCalendar;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final PostRepository postRepository;

    public MessageService(MessageRepository messageRepository, PostRepository postRepository) {
        this.messageRepository = messageRepository;
        this.postRepository = postRepository;
    }

    public void save(Message message, int postId, HttpServletRequest request) {
        var user = (User) request.getSession().getAttribute("user");
        message.setAuthor(user);
        message.setCreated(GregorianCalendar.getInstance());
        postRepository.findById(postId).addMessage(message);
        messageRepository.save(message);
    }
}
