package ru.tbcarus.funnyqueue.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tbcarus.funnyqueue.model.User;
import ru.tbcarus.funnyqueue.model.dto.*;
import ru.tbcarus.funnyqueue.service.AuthService;
import ru.tbcarus.funnyqueue.service.UserService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Registration & Authentication")
public class AuthController {
    public static final String USER_URL = "/api/user";
    public static final String REGISTER_URL = "/api/user/register";
    public static final String LOGIN_URL = "/api/user/login";
    public static final String REFRESH_TOKEN_URL = "/api/user/refresh";

    private final AuthService authService;

    @Operation(summary = "User registration")
    @PostMapping(REGISTER_URL)
    public ResponseEntity<Void> register(@Validated @RequestBody UserRegisterDto userRegisterDto) {
        User savedUser = authService.register(userRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "User authentication")
    @PostMapping(LOGIN_URL)
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest));
    }

    @Operation(summary = "Take new access token")
    @PostMapping(REFRESH_TOKEN_URL)
    public ResponseEntity<RefreshResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.refreshToken(refreshRequest.getRefreshToken()));
    }

    @GetMapping(USER_URL+"/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.status(HttpStatus.OK).body("All good!");
    }

}
