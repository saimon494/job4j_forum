package ru.job4j.forum.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.User;

public interface UserCrudRep extends CrudRepository<User, Integer> {

    User findByUsername(String name);
//
//    User findUserByNameAndPassword(String name, String password);
}
