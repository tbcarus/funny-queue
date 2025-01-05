package ru.tbcarus.funnyqueue.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tbcarus.funnyqueue.model.User;
import ru.tbcarus.funnyqueue.model.dto.UserDto;
import ru.tbcarus.funnyqueue.model.dto.UserMapper;
import ru.tbcarus.funnyqueue.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserMapper userMapper;

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
    @Transactional
    public void updateUser(String email, UserDto userDto) {
        User user = getUserByEmail(email);
        if(userDto.getName() != null && !userDto.getName().isEmpty()) {
            user.setName(userDto.getName());
        }
        if(userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(userDto.getPassword());
        }
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username).orElseThrow(EntityNotFoundException::new);
    }

    public List<UserDto> getQueueOwners() {
        List<User> users = userRepository.findAllQueueOwners();
        return users.stream().map(u -> userMapper.toUserDto(u)).collect(Collectors.toList());
    }
}
