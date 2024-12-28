package ru.tbcarus.funnyqueue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.tbcarus.funnyqueue.model.Role;
import ru.tbcarus.funnyqueue.model.User;
import ru.tbcarus.funnyqueue.repository.UserRepository;
import ru.tbcarus.funnyqueue.service.AdminUserService;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    AdminUserService service;

    //OK
    @GetMapping()
    public List<User> getUsers() {
        return service.getAll();
    }

    //OK
    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        return service.getUserById(id);
    }

    //OK
    @GetMapping("/{email}")
    public User getByEmail(@PathVariable String email) {
        return service.getUserByEmail(email);
    }

    //OK
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        service.create(user);
    }

    //OK
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        service.updateUser(id, user);
    }

    //OK
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        service.deleteUser(id);
    }

    //OK
    @PatchMapping("/{id}/enable")
    public void reverseEnableUser(@PathVariable int id) {
        service.reverseEnableUser(id);
    }

}
