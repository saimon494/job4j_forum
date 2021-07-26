package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserCrudRep;

@Service
public class UserService {

    private final UserCrudRep userCrudRep;

    public UserService(UserCrudRep userCrudRep) {
        this.userCrudRep = userCrudRep;
    }

    @Transactional
    public void save(User user) {
        userCrudRep.save(user);
    }

    public User findByName(String name) {
        return userCrudRep.findFirstByName(name);
    }

    public User findAuth(String name, String password) {
        return userCrudRep.findUserByNameAndPassword(name, password);
    }
}
