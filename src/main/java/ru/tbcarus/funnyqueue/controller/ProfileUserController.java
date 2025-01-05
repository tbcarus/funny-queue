package ru.tbcarus.funnyqueue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.tbcarus.funnyqueue.model.User;
import ru.tbcarus.funnyqueue.model.dto.UserDto;
import ru.tbcarus.funnyqueue.service.UserService;

@RestController
@RequestMapping("/profile")
public class ProfileUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public User getProfile(@AuthenticationPrincipal User user) {
        return userService.getUserByEmail(user.getEmail());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProfile(@RequestBody UserDto userDto, @AuthenticationPrincipal User user) {
        userService.updateUser(user.getEmail(), userDto);
    }

}
