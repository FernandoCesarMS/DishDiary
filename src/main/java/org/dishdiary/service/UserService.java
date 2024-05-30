package org.dishdiary.service;

import org.dishdiary.domain.users.User;
import org.dishdiary.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public int save(User review) {
        User entitySavedInDB = userRepository.save(review);

        return entitySavedInDB.getId();
    }
}
