package ru.job4j.forum.repository;

import ru.job4j.forum.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

//@Repository
public class UserRepository {

    private static final AtomicInteger USER_ID = new AtomicInteger(0);
    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private UserRepository() {
        users.put(1, User.of("admin", "admin"));
    }

    public void save(User user) {
        if (user.getId() == 0) {
            user.setId(USER_ID.incrementAndGet());
        }
        users.put(user.getId(), user);
    }

    public User findByName(String name) {
        return findUnique(user -> user.getUsername().equals(name));
    }

    public User findAuth(String name, String password) {
        return findUnique(user -> user.getUsername().equals(name)
                && user.getPassword().equals(password));
    }

    private User findUnique(Predicate<User> filter) {
        return users.values().stream()
                .filter(filter)
                .findFirst()
                .orElse(null);
    }
}
