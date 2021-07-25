package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MessageRepository {

    private static final AtomicInteger MESSAGE_ID = new AtomicInteger(0);
    private final Map<Integer, Message> messages = new ConcurrentHashMap<>();

    public void save(Message message) {
        if (message.getId() == 0) {
            message.setId(MESSAGE_ID.incrementAndGet());
        }
        messages.put(message.getId(), message);
    }
}
