package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.MessageCrudRep;
import ru.job4j.forum.repository.PostCrudRep;

import javax.servlet.http.HttpServletRequest;
import java.util.GregorianCalendar;

@Service
public class MessageService {

    private final MessageCrudRep messageCrudRep;
    private final PostCrudRep postCrudRep;

    public MessageService(MessageCrudRep messageCrudRep, PostCrudRep postCrudRep) {
        this.messageCrudRep = messageCrudRep;
        this.postCrudRep = postCrudRep;
    }

    @Transactional
    public void save(Message message, int postId, HttpServletRequest request) {
        var user = (User) request.getSession().getAttribute("user");
        message.setAuthor(user);
        message.setCreated(GregorianCalendar.getInstance());
        message.setPost(postCrudRep.findById(postId).orElse(null));
        messageCrudRep.save(message);
    }
}
