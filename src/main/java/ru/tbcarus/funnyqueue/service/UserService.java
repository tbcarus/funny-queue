package ru.tbcarus.funnyqueue.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import ru.tbcarus.funnyqueue.model.User;
import ru.tbcarus.funnyqueue.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

    @Modifying
    public User create(User user) {
        return userRepository.save(user);
    }

    @Modifying
    public void updateUser(int id, User user) {
        userRepository.save(user);
    }

    @Modifying
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Modifying
    public void reverseEnableUser(int id) {
        userRepository.reverseEnableUser(id);
    }

    public boolean existById(Integer id) {
        if (id == null) {
            return false;
        }
        return userRepository.existsById(id);
    }
}
