package ru.job4j.forum.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityRep;
import ru.job4j.forum.repository.UserCrudRep;

@Service
public class UserService {

    private final UserCrudRep userCrudRep;
    private final AuthorityRep authorityRep;
    private final PasswordEncoder encoder;

    public UserService(UserCrudRep userCrudRep,
                       AuthorityRep authorityRep,
                       PasswordEncoder encoder) {
        this.userCrudRep = userCrudRep;
        this.authorityRep = authorityRep;
        this.encoder = encoder;
    }

    @Transactional
    public void save(User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorityRep.findByAuthority("ROLE_USER"));
        userCrudRep.save(user);
    }

    public User findByUsername(String name) {
        return userCrudRep.findByUsername(name);
    }
}
