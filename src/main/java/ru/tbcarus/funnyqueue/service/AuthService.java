package ru.tbcarus.funnyqueue.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tbcarus.funnyqueue.exception.EntityAlreadyExistException;
import ru.tbcarus.funnyqueue.exception.EntityNotFoundException;
import ru.tbcarus.funnyqueue.model.User;
import ru.tbcarus.funnyqueue.model.dto.*;
import ru.tbcarus.funnyqueue.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRegisterMapper userRegisterMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public User register(UserRegisterDto userRegisterDto) {
        if (userRepository.existsByEmail(userRegisterDto.getEmail().toLowerCase())) {
            throw new EntityAlreadyExistException(userRegisterDto.getEmail(), String.format("User %s already exist", userRegisterDto.getEmail()));
        }
        User user = userRegisterMapper.toUser(userRegisterDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = getUserByEmail(loginRequest.getEmail());
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new EntityNotFoundException(loginRequest.getEmail(), String.format("User %s not found", loginRequest.getEmail()));
        }
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new LoginResponse(accessToken, refreshToken);
    }

    public RefreshResponse refreshToken(String refreshToken) {
        User user = getUserByEmail(jwtService.extractUserName(refreshToken));
        return new RefreshResponse(jwtService.refreshAccessToken(user, refreshToken));
    }

    private User getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException(email, String.format("User %s does not exist", email));
        }
        return optionalUser.get();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username).get();
    }
}
