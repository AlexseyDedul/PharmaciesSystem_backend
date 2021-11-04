package by.project.pharmases_system.controller;

import by.project.pharmases_system.model.User;
import by.project.pharmases_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getListUsers(@RequestParam(required = false) String surname) {
        return userService.getListUsers(surname).getBody();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id).getBody();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User userBody) {
        return userService.createUser(userBody);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userBody) {
        return userService.updateUser(id, userBody);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
