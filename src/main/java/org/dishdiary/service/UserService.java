package org.dishdiary.service;

import org.dishdiary.domain.requests.ValidateUserRequest;
import org.dishdiary.domain.users.User;
import org.dishdiary.domain.users.UserRepository;
import org.dishdiary.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByCpf(String cpf) {
        if (cpf == null) {
            return null;
        }
        Optional<User> optionalUser = userRepository.findByCpf(cpf);

        return optionalUser.orElse(null);
    }

    public boolean isLoginCorrect(String cpf, String password) {
        if (cpf == null || password == null) {
            return Boolean.FALSE;
        }

        Optional<User> optionalUser = userRepository.findByCpf(cpf);

        return optionalUser.map(
                    user -> user.getSenha().equals(HashUtils.encryptSHA256(password))
                )
                .orElse(Boolean.FALSE);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public int save(User review) {
        review.setSenha(HashUtils.encryptSHA256(review.getSenha()));
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
