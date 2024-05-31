package org.dishdiary.service;

import org.dishdiary.domain.requests.ValidateUserRequest;
import org.dishdiary.domain.users.User;
import org.dishdiary.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByCpf(String cpf) {
        if (cpf == null) {
            return null;
        }
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .filter(user -> cpf.equals(user.getCpf()))
                .findFirst()
                .orElse(null);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public int save(User review) {
        User entitySavedInDB = userRepository.save(review);

        return entitySavedInDB.getId();
    }

    public boolean validateIfUserExists(ValidateUserRequest request) {
        if (request == null || request.getCpf() == null || request.getSenha() == null) {
            return false;
        }

        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .anyMatch(
                        user -> request.getCpf().equals(user.getCpf()) && request.getSenha().equals(user.getSenha())
                );
    }
}
