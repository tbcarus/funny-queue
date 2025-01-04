package ru.tbcarus.funnyqueue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tbcarus.funnyqueue.model.User;
import ru.tbcarus.funnyqueue.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    UserService service;

    @GetMapping()
    public List<User> getUsers() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @GetMapping("/by-email/{email}")
    public User getByEmail(@PathVariable String email) {
        return service.getUserByEmail(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saved = service.create(user);
        return ResponseEntity.created(URI.create("/admin/users/" + saved.getId())).body(saved);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if(!service.existById(user.getId())) {
            user.setId(null);
            return  createUser(user);
        } else {
            return ResponseEntity.ok(service.create(user));
        }
    }

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
