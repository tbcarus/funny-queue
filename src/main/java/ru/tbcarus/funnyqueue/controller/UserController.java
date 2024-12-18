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
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public User getProfile(@AuthenticationPrincipal User user) {
        return userService.getUserById(user.getId());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProfile(@RequestBody User toUpdate, @AuthenticationPrincipal User user) {
        userService.updateUser(user.getId(), toUpdate);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable int id, @AuthenticationPrincipal User user) {
        return userService.getUserDtoById(id);
    }

    @GetMapping("/{email}")
    public UserDto getUserById(@PathVariable String email, @AuthenticationPrincipal User user) {
        return userService.getUserDtoByEmail(email);
    }
}
