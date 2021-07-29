package ru.job4j.forum.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.repository.MessageCrudRep;
import ru.job4j.forum.repository.PostCrudRep;
import ru.job4j.forum.repository.UserCrudRep;

import java.util.GregorianCalendar;

@Service
public class MessageService {

    private final MessageCrudRep messageCrudRep;
    private final PostCrudRep postCrudRep;
    private final UserCrudRep userCrudRep;

    public MessageService(MessageCrudRep messageCrudRep,
                          PostCrudRep postCrudRep,
                          UserCrudRep userCrudRep) {
        this.messageCrudRep = messageCrudRep;
        this.postCrudRep = postCrudRep;
        this.userCrudRep = userCrudRep;
    }

    @Transactional
    public void save(Message message, int postId) {
        message.setAuthor(userCrudRep.findByUsername(SecurityContextHolder
                .getContext().getAuthentication().getName()));
        message.setCreated(GregorianCalendar.getInstance());
        message.setPost(postCrudRep.findById(postId).orElse(null));
        messageCrudRep.save(message);
    }
}
