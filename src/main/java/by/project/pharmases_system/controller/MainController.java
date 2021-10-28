package by.project.pharmases_system.controller;

import by.project.pharmases_system.exceptions.NotFoundException;
import by.project.pharmases_system.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("main")
public class MainController {
    private Long count = 4L;
    private List<User> users = new ArrayList<User>(){{
        add(new User(1L, "Dedul", "Alex", "123213", "214324wer", "wfewef", "sgs", "ert34"));
        add(new User(2L, "Letsina", "Margo", "323434", "rewqrwe", "wefwef", "fgwf", "wrtwetr"));
        add(new User(3L, "Martynov", "Egor", "54546757", "42342r", "wfweffg", "eheh", "werwer"));
    }};

    @GetMapping
    public List<User> mainPage(){
        return users;
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable Long id){
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        user.setId(count++);
        users.add(user);
        return user;
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        User userFromDB = getUser(id);
        userFromDB = user;
        userFromDB.setId(id);
        users.set(Math.toIntExact(id - 1), userFromDB);
        return userFromDB;
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id){
        User userFromDB = getUser(id);

        users.remove(userFromDB);
    }
}
