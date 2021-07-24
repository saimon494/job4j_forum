package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Message;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private final List<Message> messages = new ArrayList<>();
    private int lastId = 0;

    public void save(Message message) {
        if (message.getId() == 0) {
            create(message);
        } else {
            update(message);
        }
    }

    private void create(Message message) {
        message.setId(++lastId);
        messages.add(message);
    }

    private void update(Message message) {
        int id = messages.indexOf(message);
        if (id != -1) {
            messages.set(id, message);
        }
    }
}
